package com.example.gateway.controllers;

import com.example.gateway.dto.PetDto;
import com.example.gateway.messaging.PetMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@Tag(name = "Pets")
@SecurityRequirement(name = "basicAuth")
@RequiredArgsConstructor
public class PetController {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    private static final String EXCHANGE = "pet-exchange";
    private static final String ROUTING_KEY = "pet.request";

    @Operation(summary = "Создание нового питомца", description = "Создает нового питомца")
    @ApiResponse(responseCode = "200", description = "Питомец успешно создан")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or authentication.principal.ownerId == #petDto.ownerId")
    public PetDto createPet(@RequestBody PetDto petDto) {
        PetMessage message = new PetMessage();
        message.setAction("CREATE");
        message.setPetDto(petDto);
        Object response = rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, message);
        return objectMapper.convertValue(response, PetDto.class);
    }

    @Operation(summary = "Удаление питомца по ID", description = "Удаляет питомца по его ID")
    @ApiResponse(responseCode = "200", description = "Питомец успешно удален или не найден")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or authentication.principal.ownerId == @petController.getPetOwnerId(#id)")
    public void deletePetById(@PathVariable long id) {
        PetMessage message = new PetMessage();
        message.setAction("DELETE");
        PetDto petDto = new PetDto();
        petDto.setId(id);
        message.setPetDto(petDto);
        rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, message);
    }

    @Operation(summary = "Удаление всех питомцев", description = "Удаляет всех питомцев")
    @ApiResponse(responseCode = "200", description = "Все питомцы успешно удалены")
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllPets() {
        PetMessage message = new PetMessage();
        message.setAction("DELETE_ALL");
        rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, message);
    }

    @Operation(summary = "Обновление питомца по ID", description = "Обновляет данные питомца по его ID")
    @ApiResponse(responseCode = "200", description = "Данные питомца успешно обновлены")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or authentication.principal.ownerId == @petController.getPetOwnerId(#id)")
    public PetDto update(@PathVariable long id, @RequestBody PetDto petDto) {
        petDto.setId(id);
        PetMessage message = new PetMessage();
        message.setAction("UPDATE");
        message.setPetDto(petDto);
        Object response = rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, message);
        return objectMapper.convertValue(response, PetDto.class);
    }

    @Operation(summary = "Получение питомца по ID", description = "Получение информации о питомце по его ID")
    @ApiResponse(responseCode = "200", description = "Данные питомца найдены или не найдены")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public PetDto getPetById(@PathVariable long id) {
        PetMessage message = new PetMessage();
        message.setAction("GET_BY_ID");
        PetDto petDto = new PetDto();
        petDto.setId(id);
        message.setPetDto(petDto);
        Object response = rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, message);
        return objectMapper.convertValue(response, PetDto.class);
    }

    @Operation(summary = "Получение всех питомцев", description = "Получение списка всех питомцев")
    @ApiResponse(responseCode = "200", description = "Список питомцев")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<PetDto> getAll() {
        PetMessage message = new PetMessage();
        message.setAction("GET_ALL");
        Object response = rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, message);
        return objectMapper.convertValue(response, new com.fasterxml.jackson.core.type.TypeReference<List<PetDto>>() {});
    }

    public Long getPetOwnerId(Long id) {
        PetMessage message = new PetMessage();
        message.setAction("GET_OWNER_ID");
        PetDto petDto = new PetDto();
        petDto.setId(id);
        message.setPetDto(petDto);
        Object response = rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, message);
        return objectMapper.convertValue(response, Long.class);
    }
}
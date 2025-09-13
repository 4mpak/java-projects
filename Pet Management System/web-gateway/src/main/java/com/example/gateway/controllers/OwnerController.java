package com.example.gateway.controllers;

import com.example.gateway.dto.OwnerDto;
import com.example.gateway.messaging.OwnerMessage;
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
@RequestMapping("/api/owners")
@Tag(name = "Owners")
@SecurityRequirement(name = "basicAuth")
@RequiredArgsConstructor
public class OwnerController {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    private static final String EXCHANGE = "owner-exchange";
    private static final String ROUTING_KEY = "owner.request";

    @Operation(summary = "Создание нового владельца", description = "Создает нового владельца питомца")
    @ApiResponse(responseCode = "200", description = "Владелец успешно создан")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public OwnerDto createOwner(@RequestBody OwnerDto ownerDto) {
        OwnerMessage message = new OwnerMessage();
        message.setAction("CREATE");
        message.setOwnerDto(ownerDto);
        Object response = rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, message);
        return objectMapper.convertValue(response, OwnerDto.class);
    }

    @Operation(summary = "Удаление владельца по ID", description = "Удаляет владельца по его ID")
    @ApiResponse(responseCode = "200", description = "Владелец успешно удален или не найден")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOwnerById(@PathVariable long id) {
        OwnerMessage message = new OwnerMessage();
        message.setAction("DELETE");
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(id);
        message.setOwnerDto(ownerDto);
        rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, message);
    }

    @Operation(summary = "Удаление всех владельцев", description = "Удаляет всех владельцев питомцев")
    @ApiResponse(responseCode = "200", description = "Все владельцы успешно удалены")
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOwners() {
        OwnerMessage message = new OwnerMessage();
        message.setAction("DELETE_ALL");
        rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, message);
    }

    @Operation(summary = "Обновление владельца по ID", description = "Обновляет данные владельца по его ID")
    @ApiResponse(responseCode = "200", description = "Данные владельца успешно обновлены")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public OwnerDto updateOwner(@PathVariable Long id, @RequestBody OwnerDto ownerDto) {
        ownerDto.setId(id);
        OwnerMessage message = new OwnerMessage();
        message.setAction("UPDATE");
        message.setOwnerDto(ownerDto);
        Object response = rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, message);
        return objectMapper.convertValue(response, OwnerDto.class);
    }

    @Operation(summary = "Получение владельца по ID", description = "Получение информации о владельце по его ID")
    @ApiResponse(responseCode = "200", description = "Данные владельца найдены или не найдены")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public OwnerDto getOwnerById(@PathVariable long id) {
        OwnerMessage message = new OwnerMessage();
        message.setAction("GET_BY_ID");
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(id);
        message.setOwnerDto(ownerDto);
        Object response = rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, message);
        return objectMapper.convertValue(response, OwnerDto.class);
    }

    @Operation(summary = "Получение всех владельцев", description = "Получение списка всех владельцев питомцев")
    @ApiResponse(responseCode = "200", description = "Список владельцев")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<OwnerDto> getAll() {
        OwnerMessage message = new OwnerMessage();
        message.setAction("GET_ALL");
        Object response = rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, message);
        return objectMapper.convertValue(response, new com.fasterxml.jackson.core.type.TypeReference<List<OwnerDto>>() {});
    }
}
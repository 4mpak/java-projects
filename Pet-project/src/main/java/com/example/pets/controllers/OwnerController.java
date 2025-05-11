package com.example.pets.controllers;

import com.example.pets.dto.OwnerDto;
import com.example.pets.mappers.OwnerMapper;
import com.example.pets.models.Owner;
import com.example.pets.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    @Autowired
    public OwnerController(OwnerService ownerService, OwnerMapper ownerMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
    }

    @Operation(summary = "Создание нового владельца", description = "Создает нового владельца питомца")
    @ApiResponse(description = "Владелец успешно создан")
    @PostMapping
    public OwnerDto createOwner(@RequestBody OwnerDto ownerDto) {
        Owner owner = ownerMapper.transformToEntity(ownerDto);
        Owner ownerSaved = ownerService.save(owner);
        return ownerMapper.transformToDto(ownerSaved);
    }

    @Operation(summary = "Удаление владельца по ID", description = "Удаляет владельца по его ID")
    @ApiResponse(description = "Владелец успешно удален или не найден")
    @DeleteMapping("/{id}")
    public void deleteOwnerById(@PathVariable long id) {
        ownerService.deleteById(id);
    }

    @Operation(summary = "Удаление всех владельцев", description = "Удаляет всех владельцев питомцев")
    @ApiResponse(description = "Все владельцы успешно удалены")
    @DeleteMapping
    public void deleteAllOwners() {
        ownerService.deleteAll();
    }

    @Operation(summary = "Обновление владельца по ID", description = "Обновляет данные владельца по его ID")
    @ApiResponse(description = "Данные владельца успешно обновлены")
    @PutMapping("/{id}")
    public OwnerDto updateOwner(@PathVariable Long id, @RequestBody OwnerDto ownerDto) {
        Owner owner = ownerMapper.transformToEntity(ownerDto);
        Owner ownerUpdated = ownerService.update(id, owner);
        return ownerMapper.transformToDto(ownerUpdated);
    }

    @Operation(summary = "Получение владельца по ID", description = "Получение информации о владельце по его ID")
    @ApiResponse(description = "Данные владельца найдены или не найдены")
    @GetMapping("/{id}")
    public OwnerDto getOwnerById(@PathVariable long id) {
        Owner ownerGot = ownerService.getById(id);
        return ownerMapper.transformToDto(ownerGot);
    }

    @Operation(summary = "Получение всех владельцев", description = "Получение списка всех владельцев питомцев")
    @ApiResponse(description = "Список владельцев")
    @GetMapping
    public List<OwnerDto> getAll() {
        return ownerService.getAll().stream()
                .map(ownerMapper::transformToDto)
                .collect(Collectors.toList());
    }
}

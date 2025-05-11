package com.example.pets.controllers;

import com.example.pets.dto.PetDto;
import com.example.pets.mappers.PetMapper;
import com.example.pets.models.Pet;
import com.example.pets.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pets")
public class PetController
{
    private final PetService petService;
    private final PetMapper petMapper;

    @Autowired
    public PetController(PetService petService, PetMapper petMapper) {
        this.petService = petService;
        this.petMapper = petMapper;
    }

    @Operation(summary = "Создание нового питомца", description = "Создает нового питомца")
    @ApiResponse(description = "Питомец успешно создан")
    @PostMapping
    public PetDto createPet(@RequestBody PetDto petDto) {
        Pet pet = petMapper.transformToEntity(petDto);
        Pet petSaved = petService.save(pet);
        return petMapper.transformToDto(petSaved);
    }

    @Operation(summary = "Удаление питомца по ID", description = "Удаляет питомца по его ID")
    @ApiResponse(description = "Питомец успешно удален или не найден")
    @DeleteMapping("/{id}")
    public void deletePetById(@PathVariable long id) {
        petService.deleteById(id);
    }

    @Operation(summary = "Удаление всех питомцев", description = "Удаляет всех питомцев")
    @ApiResponse(description = "Все питомцы успешно удалены")
    @DeleteMapping
    public void deleteAllPets() {
        petService.deleteAll();
    }

    @Operation(summary = "Обновление питомца по ID", description = "Обновляет данные питомца по его ID")
    @ApiResponse(description = "Данные питомца успешно обновлены")
    @PutMapping("/{id}")
    public PetDto update(@PathVariable long id, @RequestBody PetDto petDto) {
        Pet pet = petMapper.transformToEntity(petDto);
        Pet petUpdated = petService.update(id, pet);
        return petMapper.transformToDto(petUpdated);
    }

    @Operation(summary = "Получение питомца по ID", description = "Получение информации о питомце по его ID")
    @ApiResponse(description = "Данные питомца найдены или не найдены")
    @GetMapping("/{id}")
    public PetDto getPetById(@PathVariable long id) {
        Pet petGot = petService.getById(id);
        return petMapper.transformToDto(petGot);
    }

    @Operation(summary = "Получение всех питомцев", description = "Получение списка всех питомцев")
    @ApiResponse(description = "Список питомцев")
    @GetMapping
    public List<PetDto> getAll() {
        return petService.getAll().stream()
                .map(petMapper::transformToDto)
                .collect(Collectors.toList());
    }
}

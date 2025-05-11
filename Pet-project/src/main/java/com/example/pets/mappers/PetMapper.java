package com.example.pets.mappers;

import com.example.pets.dto.PetDto;
import com.example.pets.models.Pet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PetMapper
{
    public PetDto transformToDto(Pet pet) {
        PetDto dto = new PetDto();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setBirthDate(pet.getBirthDate());
        dto.setBreed(pet.getBreed());
        dto.setColorPet(pet.getColorPet());
        dto.setOwnerId(pet.getOwner().getId());
        List<Long> petsId = new ArrayList<>();
        for (int i = 0; i < pet.getPetFriends().size(); i++) {
            petsId.add(pet.getPetFriends().get(i).getId());
        }
        return dto;
    }

    public Pet transformToEntity(PetDto dto) {
        Pet pet = new Pet();
        pet.setId(dto.getId());
        pet.setName(dto.getName());
        pet.setBirthDate(dto.getBirthDate());
        pet.setBreed(dto.getBreed());
        pet.setColorPet(dto.getColorPet());
        return pet;
    }
}
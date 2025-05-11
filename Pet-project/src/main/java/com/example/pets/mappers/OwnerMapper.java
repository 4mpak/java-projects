package com.example.pets.mappers;

import com.example.pets.dto.OwnerDto;
import com.example.pets.models.Owner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OwnerMapper
{
    public OwnerDto transformToDto(Owner owner){
        OwnerDto dto = new OwnerDto();
        dto.setId(owner.getId());
        dto.setName(owner.getName());
        dto.setBirthDate(owner.getBirthDate());
        List<Long> petsId = new ArrayList<>();
        for (int i = 0; i < owner.getPetList().size(); i++) {
            petsId.add(owner.getPetList().get(i).getId());
        }
        return dto;
    }

    public Owner transformToEntity(OwnerDto dto){
        Owner owner = new Owner();
        owner.setId(dto.getId());
        owner.setName(dto.getName());
        owner.setBirthDate(dto.getBirthDate());
        return owner;
    }
}

package com.example.owner.mappers;

import com.example.owner.dto.OwnerDto;
import com.example.owner.models.Owner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper
{
    OwnerDto toDto(Owner owner);
    Owner toEntity(OwnerDto ownerDto);
}

package com.example.gateway.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PetDto {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String breed;
    private ColorPet colorPet;
    private Long ownerId;
    private List<Long> friendsId;
}

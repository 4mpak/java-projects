package com.example.pets.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OwnerDto {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private List<Long> petsId;
}


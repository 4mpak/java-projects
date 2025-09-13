package com.example.pet.messaging;

import com.example.pet.dto.PetDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetMessage {
    private String action;
    private PetDto petDto;
}

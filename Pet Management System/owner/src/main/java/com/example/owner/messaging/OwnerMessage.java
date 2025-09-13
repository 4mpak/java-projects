package com.example.owner.messaging;

import com.example.owner.dto.OwnerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerMessage {
    private String action;
    private OwnerDto ownerDto;
}

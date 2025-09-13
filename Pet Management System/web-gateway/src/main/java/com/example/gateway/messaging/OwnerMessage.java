package com.example.gateway.messaging;

import com.example.gateway.dto.OwnerDto;
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

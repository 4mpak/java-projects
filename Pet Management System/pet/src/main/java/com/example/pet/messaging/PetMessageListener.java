package com.example.pet.messaging;

import com.example.pet.config.RabbitMQConfig;
import com.example.pet.dto.PetDto;
import com.example.pet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PetMessageListener {

    private final PetService petService;

    @RabbitListener(queues = RabbitMQConfig.PET_REQUEST_QUEUE)
    @SendTo
    public Object handleMessage(PetMessage message) {
        String action = message.getAction();
        PetDto result;
        List<PetDto> results;

        return switch (action) {
            case "CREATE" -> petService.save(message.getPetDto());
            case "UPDATE" -> petService.update(message.getPetDto().getId(), message.getPetDto());
            case "DELETE" -> {
                petService.deleteById(message.getPetDto().getId());
                yield "Deleted";
            }
            case "DELETE_ALL" -> {
                petService.deleteAll();
                yield "All deleted";
            }
            case "GET_BY_ID" -> petService.getById(message.getPetDto().getId());
            case "GET_ALL" -> petService.getAll();
            case "GET_OWNER_ID" -> petService.getPetOwnerId(message.getPetDto().getId());
            default -> throw new IllegalArgumentException("Unknown action: " + action);
        };
    }
}
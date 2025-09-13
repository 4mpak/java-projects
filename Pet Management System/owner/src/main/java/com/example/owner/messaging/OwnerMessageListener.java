package com.example.owner.messaging;

import com.example.owner.config.RabbitMQConfig;
import com.example.owner.dto.OwnerDto;
import com.example.owner.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OwnerMessageListener {
    private final OwnerService ownerService;

    @RabbitListener(queues = RabbitMQConfig.OWNER_REQUEST_QUEUE)
    @SendTo
    public Object handleMessage(OwnerMessage message) {
        String action = message.getAction();
        OwnerDto result;
        List<OwnerDto> results;

        return switch (action) {
            case "CREATE" -> ownerService.save(message.getOwnerDto());
            case "UPDATE" -> ownerService.update(message.getOwnerDto().getId(), message.getOwnerDto());
            case "DELETE" -> {
                ownerService.deleteById(message.getOwnerDto().getId());
                yield "Deleted";
            }
            case "DELETE_ALL" -> {
                ownerService.deleteAll();
                yield "All deleted";
            }
            case "GET_BY_ID" -> ownerService.getById(message.getOwnerDto().getId());
            case "GET_ALL" -> ownerService.getAll();
            default -> throw new IllegalArgumentException("Unknown action: " + action);
        };
    }
}
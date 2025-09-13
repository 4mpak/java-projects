package com.example.pet.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PET_REQUEST_QUEUE = "pet-request-queue";
    public static final String PET_RESPONSE_QUEUE = "pet-response-queue";
    public static final String PET_EXCHANGE = "pet-exchange";
    public static final String PET_REQUEST_ROUTING_KEY = "pet.request"; // Единый routing key
    public static final String PET_RESPONSE_ROUTING_KEY = "pet.response";

    @Bean
    public Queue petRequestQueue() {
        return new Queue(PET_REQUEST_QUEUE, true);
    }

    @Bean
    public Queue petResponseQueue() {
        return new Queue(PET_RESPONSE_QUEUE, true);
    }

    @Bean
    public TopicExchange petExchange() {
        return new TopicExchange(PET_EXCHANGE);
    }

    @Bean
    public Binding bindPetRequestQueue(Queue petRequestQueue, TopicExchange petExchange) {
        return BindingBuilder.bind(petRequestQueue).to(petExchange).with(PET_REQUEST_ROUTING_KEY);
    }

    @Bean
    public Binding bindPetResponseQueue(Queue petResponseQueue, TopicExchange petExchange) {
        return BindingBuilder.bind(petResponseQueue).to(petExchange).with(PET_RESPONSE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}
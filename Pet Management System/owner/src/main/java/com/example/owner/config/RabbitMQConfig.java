package com.example.owner.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String OWNER_REQUEST_QUEUE = "owner-request-queue";
    public static final String OWNER_RESPONSE_QUEUE = "owner-response-queue";
    public static final String OWNER_EXCHANGE = "owner-exchange";
    public static final String OWNER_REQUEST_ROUTING_KEY = "owner.request"; // Единый routing key
    public static final String OWNER_RESPONSE_ROUTING_KEY = "owner.response";

    @Bean
    public Queue ownerRequestQueue() {
        return new Queue(OWNER_REQUEST_QUEUE, true);
    }

    @Bean
    public Queue ownerResponseQueue() {
        return new Queue(OWNER_RESPONSE_QUEUE, true);
    }

    @Bean
    public TopicExchange ownerExchange() {
        return new TopicExchange(OWNER_EXCHANGE);
    }

    @Bean
    public Binding bindOwnerRequestQueue(Queue ownerRequestQueue, TopicExchange ownerExchange) {
        return BindingBuilder.bind(ownerRequestQueue).to(ownerExchange).with(OWNER_REQUEST_ROUTING_KEY);
    }

    @Bean
    public Binding bindOwnerResponseQueue(Queue ownerResponseQueue, TopicExchange ownerExchange) {
        return BindingBuilder.bind(ownerResponseQueue).to(ownerExchange).with(OWNER_RESPONSE_ROUTING_KEY);
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
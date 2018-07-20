package com.brian.springboot.mq.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PublishService {

    @Autowired
    private RabbitTemplate amqpTemplate;

    public void send(String exchange, String routingKey, Object message){
        final CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        amqpTemplate.convertAndSend(exchange,routingKey,message,correlationData);
    }
}

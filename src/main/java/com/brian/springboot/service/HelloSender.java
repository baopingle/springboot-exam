package com.brian.springboot.service;

import com.brian.springboot.configuration.RabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        String content = "Hello " + new Date();
        System.out.println("Sender: " + content);
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_HELLO,content);
    }
}
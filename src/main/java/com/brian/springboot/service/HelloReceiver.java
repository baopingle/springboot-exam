package com.brian.springboot.service;

import com.brian.springboot.configuration.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_HELLO)
public class HelloReceiver {

    @RabbitHandler
    public void process(String content){
        System.out.println("Receiver: "+content);
    }
}

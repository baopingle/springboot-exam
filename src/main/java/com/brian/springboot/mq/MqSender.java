package com.brian.springboot.mq;

import com.brian.springboot.configuration.RabbitConfig;
import com.brian.springboot.domain.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MqSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int count){
        String content = "Hello " + new Date() + "******"+count;
        System.out.println("Sender: " + content);
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_HELLO,content);
    }

    public void send(User user){
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_USER,user);
    }
}

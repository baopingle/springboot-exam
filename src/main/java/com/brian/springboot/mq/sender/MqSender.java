package com.brian.springboot.mq.sender;

import com.brian.springboot.configuration.RabbitConfig;
import com.brian.springboot.domain.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class MqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(int count){
        String content = "Hello " + new Date() + "******"+count;
        System.out.println("Sender: " + content);
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_HELLO,content);
    }

    public void send(User user){
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_USER,user);
    }

    public void sendMessage1(){
        String content = "hi, i am message 1";
        System.out.println("Sender: "+content);
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,RabbitConfig.TOPIC_QUEUE_ROUTING_MESSAGE,content);
    }

    public void sendMessage2(){
        String content = "hi, i am message 2";
        System.out.println("Sender: "+content);
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,RabbitConfig.TOPIC_ROUTING_MESSAGES,content);
    }

    public void sendFanoutMessage(){
        String content = "hi, fanout message!";
        System.out.println("Sender: "+content);
        final CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE,"", content, correlationData);
    }
}

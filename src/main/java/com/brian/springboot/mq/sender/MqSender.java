package com.brian.springboot.mq.sender;

import com.brian.springboot.configuration.FanoutRabbitConfig;
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
        rabbitTemplate.convertAndSend(FanoutRabbitConfig.FANOUT_EXCHANGE,"", content);
    }
}

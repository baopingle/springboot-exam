package com.brian.springboot.mq.receiver;

import com.brian.springboot.configuration.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_HELLO)
public class HelloReceiver {

    @RabbitHandler
    public void process1(String content){
        System.out.println("Receiver 1: "+content);
    }

    @RabbitHandler
    public void process2(String msgContent){
        System.out.println("Receiver 2: "+msgContent);
    }
}

package com.brian.springboot.mq;

import com.brian.springboot.configuration.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_HELLO)
public class HelloReceiver2 {

    @RabbitHandler
    public void process(String msgContent){
        System.out.println("Receiver 2: "+msgContent);
    }
}

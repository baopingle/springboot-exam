package com.brian.springboot.mq.receiver;

import com.brian.springboot.configuration.RabbitConfig;
import com.brian.springboot.domain.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_USER)
public class UserReceiver {

    @RabbitHandler
    public void process(User user){
        System.out.println("Receive object: "+user);
    }
}

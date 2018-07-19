package com.brian.springboot.mq.receiver;

import com.brian.springboot.configuration.FanoutRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutMsgReceiver {

    @RabbitListener(queues = FanoutRabbitConfig.FANOUT_QUEUE_A)
    @RabbitHandler
    public void process1(String content){
        System.out.println("Fanout Receiver 1: "+content);
    }

    @RabbitListener(queues = FanoutRabbitConfig.FANOUT_QUEUE_B)
    @RabbitHandler
    public void process2(String content){
        System.out.println("Fanout Receiver 2: "+content);
    }

    @RabbitListener(queues = FanoutRabbitConfig.FANOUT_QUEUE_C)
    @RabbitHandler
    public void process3(String content){
        System.out.println("Fanout Receiver 3: "+content);
    }
}

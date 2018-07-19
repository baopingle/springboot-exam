package com.brian.springboot.mq.receiver;

import com.brian.springboot.configuration.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicMsgReceiver {

    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE_ROUTING_MESSAGE)
    @RabbitHandler
    public void process1(String msgContent){
        System.out.println("Receiver 1:"+msgContent);
    }

    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE_MESSAGE_PATTERN)
    @RabbitHandler
    public void process2(String msgContent){
        System.out.println("Receiver 2:"+msgContent);
    }
}

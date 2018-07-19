package com.brian.springboot.mq.receiver;

import com.brian.springboot.configuration.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicMsgReceiver {

    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE_ROUTING_MESSAGE)
    public void process1(Message msgContent){
        System.out.println("Receiver 1:"+msgContent.toString());
    }

    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE_MESSAGE_PATTERN)
    public void process2(Message msgContent){
        System.out.println("Receiver 2:"+msgContent);
    }
}

package com.brian.springboot.mq.receiver;

import com.brian.springboot.configuration.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FanoutMsgReceiver {

    private static final Logger log = LoggerFactory.getLogger(FanoutMsgReceiver.class);

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_A)
    public void process1(Message message, Channel channel) throws IOException{
        log.info("Fanout Receiver 1: {}", message.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
    }

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_B)
    public void process2(Message message, Channel channel) throws IOException{
        log.info("Fanout Receiver 2: {}",message.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
    }

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_C)
    public void process3(Message message, Channel channel) throws IOException{
        log.info("Fanout Receiver 3: {}",message.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
    }
}

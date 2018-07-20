package com.brian.springboot.mq.receiver;

import com.brian.springboot.configuration.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FanoutMsgReceiver {

    private static final Logger log = LoggerFactory.getLogger(FanoutMsgReceiver.class);

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_A)
    public void process1(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException{
        log.info("Fanout Receiver 1: {} - {}", tag,message.toString());
        channel.basicAck(tag,false);
    }

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_B)
    public void process2(Message message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException{
        log.info("Fanout Receiver 2: {} - {}", tag,message.toString());
        channel.basicAck(tag,false);
    }

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_C)
    public void process3(Message message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException{
        log.info("Fanout Receiver 3: {} - {}",tag,message.toString());
        channel.basicAck(tag,false);
    }
}

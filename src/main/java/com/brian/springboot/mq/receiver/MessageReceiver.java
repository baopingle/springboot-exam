package com.brian.springboot.mq.receiver;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver implements ChannelAwareMessageListener {
    private static final Logger log = LoggerFactory.getLogger(MessageReceiver.class);

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            log.info("consumer-----:"+message.getMessageProperties()+":"+new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            log.error("error!",e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
        }
    }
}

package com.brian.springboot.mq.receiver;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceiver implements ChannelAwareMessageListener {
    private static final Logger log = LoggerFactory.getLogger(FanoutReceiver.class);

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        log.info("FanoutReceiver {}: {}",message.getMessageProperties().getCorrelationId(), message.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}

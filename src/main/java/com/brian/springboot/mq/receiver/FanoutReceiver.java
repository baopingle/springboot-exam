package com.brian.springboot.mq.receiver;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FanoutReceiver implements ChannelAwareMessageListener,RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    private static final Logger log = LoggerFactory.getLogger(FanoutReceiver.class);


    @PostConstruct
    public void init(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setEncoding("UTF-8");
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        log.info("FanoutReceiver {}", message.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack){
            log.info("message send to exchange successfully, id: {}",correlationData.getId());
        }else{
            log.info("message send to exchange failed, caused by {}",cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        String correlationId = message.getMessageProperties().getCorrelationId();
        log.info("Message: {} send failure, reply code: {}, reason: {}, Exchange: {}, routing key: {} ",
                correlationId, replyCode, replyText, exchange, routingKey);
    }
}

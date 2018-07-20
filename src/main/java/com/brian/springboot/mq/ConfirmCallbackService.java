package com.brian.springboot.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

@Service
public class ConfirmCallbackService implements RabbitTemplate.ConfirmCallback {
    private static final Logger log = LoggerFactory.getLogger(ConfirmCallbackService.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack){
            log.info("Confirm message send to exchange successfully, id: {}",correlationData);
        }else{
            log.info("Confirm message send to exchange failed, caused by {}",cause);
        }
    }
}

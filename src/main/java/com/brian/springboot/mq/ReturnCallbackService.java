package com.brian.springboot.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReturnCallbackService implements RabbitTemplate.ReturnCallback {

    private static final Logger log = LoggerFactory.getLogger(ReturnCallbackService.class);

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        String correlationId = message.getMessageProperties().getCorrelationId();
        log.info("Return Message: {} send failure, reply code: {}, reason: {}, Exchange: {}, routing key: {} ",
                correlationId, replyCode, replyText, exchange, routingKey);
    }
}

package com.brian.springboot.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_HELLO = "hello";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_HELLO);
    }
}

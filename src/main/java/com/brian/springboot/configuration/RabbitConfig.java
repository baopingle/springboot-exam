package com.brian.springboot.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_HELLO = "hello";
    public static final String QUEUE_USER = "user";

    @Bean(name = QUEUE_HELLO)
    public Queue helloQueue(){
        return new Queue(QUEUE_HELLO);
    }

    @Bean(name = QUEUE_USER)
    public Queue userQueue(){
        return new Queue(QUEUE_USER);
    }
}

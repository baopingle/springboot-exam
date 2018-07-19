package com.brian.springboot.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_HELLO = "hello";
    public static final String QUEUE_USER = "user";
    public static final String TOPIC_EXCHANGE = "exchange";
    public static final String TOPIC_QUEUE_ROUTING_MESSAGE ="topic.message";
    public static final String TOPIC_ROUTING_MESSAGES="topic.messages";
    public static final String TOPIC_QUEUE_MESSAGE_PATTERN ="topic.#";


    @Bean(name = QUEUE_HELLO)
    public Queue helloQueue(){
        return new Queue(QUEUE_HELLO);
    }

    @Bean(name = QUEUE_USER)
    public Queue userQueue(){
        return new Queue(QUEUE_USER);
    }

    @Bean
    public Queue topicMessage(){
        return new Queue(TOPIC_QUEUE_ROUTING_MESSAGE);
    }

    @Bean
    public Queue topicMessages(){
        return new Queue(TOPIC_QUEUE_MESSAGE_PATTERN);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeMessage(Queue topicMessage, TopicExchange exchange){
        return BindingBuilder.bind(topicMessage).to(exchange).with(TOPIC_QUEUE_ROUTING_MESSAGE);
    }

    @Bean
    Binding bindingExchangeMessages(Queue topicMessages, TopicExchange exchange){
        return BindingBuilder.bind(topicMessages).to(exchange).with(TOPIC_QUEUE_MESSAGE_PATTERN);
    }
}

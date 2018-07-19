package com.brian.springboot.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig {

    public static final String FANOUT_EXCHANGE = "fanoutExchange";
    public static final String FANOUT_QUEUE_A = "fanout.A";
    public static final String FANOUT_QUEUE_B = "fanout.B";
    public static final String FANOUT_QUEUE_C = "fanout.C";

    @Bean
    public Queue messageA(){
        return new Queue(FANOUT_QUEUE_A);
    }

    @Bean
    public Queue messageB(){
        return new Queue(FANOUT_QUEUE_B);
    }

    @Bean
    public Queue messageC(){
        return new Queue(FANOUT_QUEUE_C);
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeA(Queue messageA, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(messageA).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue messageB, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(messageB).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue messageC, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(messageC).to(fanoutExchange);
    }
}

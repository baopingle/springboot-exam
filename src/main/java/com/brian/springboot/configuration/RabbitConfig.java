package com.brian.springboot.configuration;

import com.brian.springboot.mq.ConfirmCallbackService;
import com.brian.springboot.mq.ReturnCallbackService;
import com.brian.springboot.mq.receiver.FanoutMsgReceiver;
import com.brian.springboot.mq.receiver.FanoutReceiver;
import com.brian.springboot.mq.receiver.MessageReceiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    public static final String FANOUT_EXCHANGE = "fanoutExchange";
    public static final String FANOUT_QUEUE_A = "fanout.A";
    public static final String FANOUT_QUEUE_B = "fanout.B";
    public static final String FANOUT_QUEUE_C = "fanout.C";

    public static final String DIRECT_EXCHANGE = "directExchange";
    public static final String QUEUE_DIRECT = "queue_direct";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageReceiver receiver;

    @Autowired
    private ConfirmCallbackService confirmCallbackService;

    @Autowired
    private ReturnCallbackService returnCallbackService;

    @Bean
    public MessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(){
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setEncoding("UTF-8");
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        rabbitTemplate.setReturnCallback(returnCallbackService);
        rabbitTemplate.setConfirmCallback(confirmCallbackService);
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory conectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(conectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter());
        return factory;
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addQueueNames(QUEUE_DIRECT);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(receiver);
        container.setMessageConverter(jackson2JsonMessageConverter());
        return container;
    }

    @Bean(name = QUEUE_HELLO)
    public Queue helloQueue(){
        return new Queue(QUEUE_HELLO);
    }

    @Bean(name = QUEUE_USER)
    public Queue userQueue(){
        return new Queue(QUEUE_USER);
    }

    @Bean(name=QUEUE_DIRECT)
    public Queue directQueue(){
        return QueueBuilder.durable(QUEUE_DIRECT).build();
    }

    @Bean
    DirectExchange directExchange(){
        return (DirectExchange)ExchangeBuilder.directExchange(DIRECT_EXCHANGE).durable(true).build();
    }

    @Bean
    Binding bindingDirectExchangeMessage(@Qualifier(QUEUE_DIRECT) Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_DIRECT);
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

    @Bean(name=FANOUT_EXCHANGE)
    FanoutExchange fanoutExchange(){
        return (FanoutExchange)ExchangeBuilder.fanoutExchange(FANOUT_EXCHANGE).durable(true).build();
    }

    @Bean(name=FANOUT_QUEUE_A)
    public Queue fanoutQueueA(){
        return QueueBuilder.durable(FANOUT_QUEUE_A).build();
    }

    @Bean(name=FANOUT_QUEUE_B)
    public Queue fanoutQueueB(){
        return QueueBuilder.durable(FANOUT_QUEUE_B).build();
    }

    @Bean(name=FANOUT_QUEUE_C)
    public Queue fanoutQueueC(){
        return QueueBuilder.durable(FANOUT_QUEUE_C).build();
    }

    @Bean
    Binding bindingExchangeMessage(Queue topicMessage, TopicExchange exchange){
        return BindingBuilder.bind(topicMessage).to(exchange).with(TOPIC_QUEUE_ROUTING_MESSAGE);
    }

    @Bean
    Binding bindingExchangeMessages(Queue topicMessages, TopicExchange exchange){
        return BindingBuilder.bind(topicMessages).to(exchange).with(TOPIC_QUEUE_MESSAGE_PATTERN);
    }

    @Bean
    Binding bindingA(@Qualifier(FANOUT_QUEUE_A) Queue queue, @Qualifier(FANOUT_EXCHANGE) FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    Binding bindingB(@Qualifier(FANOUT_QUEUE_B) Queue queue, @Qualifier(FANOUT_EXCHANGE) FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    Binding bindingC(@Qualifier(FANOUT_QUEUE_C) Queue queue, @Qualifier(FANOUT_EXCHANGE) FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
}

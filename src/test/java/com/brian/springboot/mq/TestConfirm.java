package com.brian.springboot.mq;

import com.brian.springboot.configuration.RabbitConfig;
import com.brian.springboot.mq.sender.PublishService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestConfirm {

    private static final Logger log = LoggerFactory.getLogger(TestConfirm.class);

    @Autowired
    private PublishService publishService;

    @After
    public void afterTest() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    public void test1() {
        String message = "currentTime: "+System.currentTimeMillis();
        log.info("test1-------message: "+message);
        // exchange, routingKey are correct, then confirm will be invoked, ack=true
        publishService.send(RabbitConfig.DIRECT_EXCHANGE,RabbitConfig.QUEUE_DIRECT, message);
    }

    @Test
    public void test2(){
        String message = "currentTime: "+System.currentTimeMillis();
        log.info("test2-------message: "+message);
        // exchange is wrong,routingKey is correct, then confirm will be invoked, ack=false
        publishService.send("noexchange", RabbitConfig.QUEUE_DIRECT, message);
    }

    @Test
    public void test3(){
        String message = "currentTime: "+System.currentTimeMillis();
        log.info("test3-------message: "+message);
        // exchange is correct,routingKey is wrong, then confirm will be invoked, ack=true, return will be invoked (replyText: NO_ROUTE)
        publishService.send(RabbitConfig.DIRECT_EXCHANGE, "", message);
    }

    @Test
    public void test4(){
        String message = "currentTime: "+System.currentTimeMillis();
        log.info("test4-------message: "+message);
        // exchange, routingKey are wrong, then confirm will be invoked, ack=false
        publishService.send("noexchange", "", message);
    }
}

package com.brian.springboot.mq;

import com.brian.springboot.domain.User;
import com.brian.springboot.mq.sender.MqSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RabbitMqTest {
    @Autowired
    private MqSender sender;

    @Test
    public void hello(){
        for(int i=0;i<100;i++){
            sender.send(i);
        }

    }

    @Test
    public void user(){
        User user = new User();
        user.setId(1l);
        user.setLoginName("Brian2");
        sender.send(user);
    }

    @Test
    public void topicMessage(){
        sender.sendMessage1();
    }

    @Test
    public void topicMessages(){
        sender.sendMessage2();
    }

    @Test
    public void fanoutMessage(){
        sender.sendFanoutMessage();
    }
}

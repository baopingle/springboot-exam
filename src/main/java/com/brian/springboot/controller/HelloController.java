package com.brian.springboot.controller;

import com.brian.springboot.configuration.UserConfig;
import com.brian.springboot.domain.Gender;
import com.brian.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private UserConfig userConfig;

    @RequestMapping(value = "/hello")
    public String hello(){
        return "Hello, Spring boot! I'm here!!!";
    }

    @RequestMapping(value="/user")
    public User getUser(@RequestParam(name="username", required=false) String userName){
        User user = new User();
        if(!StringUtils.isEmpty(userName)) {
            user.setName(userName);
        }else{
            user.setName(userConfig.getUsrId());
        }
        user.setPassword(userConfig.getPassword());
        user.setAge(userConfig.getAge());
        user.setSex(Gender.toGender(userConfig.getSex()));
        return user;
    }
}

package com.brian.springboot.controller;

import com.brian.springboot.component.UserConfig;
import com.brian.springboot.domain.Gender;
import com.brian.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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
            user.setLoginName(userName);
        }else{
            user.setLoginName(userConfig.getUsrId());
        }
        user.setPassword(userConfig.getPassword());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime birthDate = LocalDateTime.parse(userConfig.getBirthDate(), df);
        user.setBirthDate(birthDate);
        user.setGender(Gender.toGender(userConfig.getSex()));
        return user;
    }

    @RequestMapping("/uid")
    public String uid(HttpSession session){
        UUID uid = (UUID) session.getAttribute("uid");
        if(uid == null){
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
}

package com.brian.springboot.controller;

import com.brian.springboot.domain.User;
import com.brian.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUserByLoginName")
    public User getUserByLoginName(String loginName){
        return userService.findUser(loginName);
    }

    @RequestMapping(path="create/{loginName}", method= RequestMethod.GET)
    public User createUser(@PathVariable("loginName") String loginName){
        return userService.createUser(loginName);
    }
}

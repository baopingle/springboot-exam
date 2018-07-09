package com.brian.springboot.service;

import com.brian.springboot.dao.UserRepository;
import com.brian.springboot.domain.Gender;
import com.brian.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    public User findUser(String loginName){
        return UserRepository.findUserByLoginName(loginName);
    }

    public User getUser(Long id){
        return UserRepository.getOne(id);
    }

    @Transactional(rollbackFor=Exception.class)
    public User createUser(String loginName){
        User temp = new User();
        temp.setLoginName(loginName);
        temp.setGender(Gender.Male);
        temp.setFirstName("First_name");
        temp.setLastName("Last_name");
        temp.setPassword("test");

        return UserRepository.save(temp);
    }
}

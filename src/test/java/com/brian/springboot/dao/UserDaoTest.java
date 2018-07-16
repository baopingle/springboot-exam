package com.brian.springboot.dao;

import com.brian.springboot.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSaveUser() throws Exception{
        User user = new User();
        user.setId(2l);
        user.setLoginName("Brian");
        user.setPassword("789000");
        userDao.saveUser(user);
    }

    @Test
    public void testFindUserByLoginName(){
        User user = userDao.findUserByLoginName("Brian");
        Assert.assertEquals("789000",user.getPassword());
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(2l);
        user.setFirstName("My First Name");
        user.setLastName("My Last Name");
        userDao.updateUser(user);
    }

    @Test
    public void testDeleteUser(){
        userDao.deleteUserById(2l);
    }
}

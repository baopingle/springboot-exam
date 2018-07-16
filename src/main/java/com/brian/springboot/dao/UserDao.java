package com.brian.springboot.dao;

import com.brian.springboot.domain.User;

public interface UserDao {

    void saveUser(User user);

    User findUserByLoginName(String loginName);

    void updateUser(User user);

    void deleteUserById(Long id);
}

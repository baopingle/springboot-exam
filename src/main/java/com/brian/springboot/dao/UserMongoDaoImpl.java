package com.brian.springboot.dao;

import com.brian.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class UserMongoDaoImpl implements UserDao{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public User findUserByLoginName(String loginName) {
        Query query = new Query(Criteria.where("loginName").is(loginName));
        return mongoTemplate.findOne(query,User.class);
    }

    @Override
    public void updateUser(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update().set("firstName",user.getFirstName()).set("lastName", user.getLastName());
        mongoTemplate.updateFirst(query,update,User.class);
    }

    @Override
    public void deleteUserById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,User.class);
    }
}

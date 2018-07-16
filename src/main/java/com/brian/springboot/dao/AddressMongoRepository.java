package com.brian.springboot.dao;

import com.brian.springboot.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AddressMongoRepository extends MongoRepository<Address, String> {
    List<Address> findByCityAndCountry(String city, String country);
}

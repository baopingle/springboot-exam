package com.brian.springboot.service;

import com.brian.springboot.dao.AddressMongoRepository;
import com.brian.springboot.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressMongoRepository addressMongoRepository;

    public void saveAddress(Address address){
        addressMongoRepository.save(address);
    }

    public Address findFirstByCityAndCountry(String city, String country) {
        return addressMongoRepository.findByCityAndCountry(city, country).stream().findFirst().orElse(null);
    }
}

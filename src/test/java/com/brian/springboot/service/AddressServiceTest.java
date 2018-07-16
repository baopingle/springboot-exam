package com.brian.springboot.service;

import com.brian.springboot.domain.Address;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AddressServiceTest {

    @Autowired
    AddressService addressService;

    @Test
    public void testSaveAddress(){
        Address address = new Address();
        address.setId(getId());
        address.setCity("Shanghai");
        address.setCountry("China");
        address.setOffice("Nanjing west road 388, CIROS");
        address.setHome("JiangSu road");
        addressService.saveAddress(address);
    }

    @Test
    public void testFindFirstByCityAndCountry(){
        Address result = addressService.findFirstByCityAndCountry("Shanghai","China");
        Assert.assertEquals("JiangSu road",result.getHome());
    }

    private String getId(){
        LocalDate localDate = LocalDate.of(2018,1,1);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return new ObjectId(date).toString();
    }
}

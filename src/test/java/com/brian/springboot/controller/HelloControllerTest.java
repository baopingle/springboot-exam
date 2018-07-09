package com.brian.springboot.controller;

import com.brian.springboot.domain.Gender;
import com.brian.springboot.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class HelloControllerTest extends AbstractControllerTest{

    private User mockUser;

    @Autowired
    private HelloController helloController;

    private static DateTimeFormatter df;

    @BeforeClass
    public static void beforeConstruct(){
        df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Before
    public void init(){
        mockUser = new User();
        mockUser.setGender(Gender.Male);
        mockUser.setBirthDate(LocalDateTime.parse("1980-12-14 00:00:00", df));
        mockUser.setLoginName("DEV-PC");
        mockUser.setPassword("8ik,(OL>");
    }

    @Test
    public void testGetUserWhenNoParameters() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(mockUser);
        mvc.perform(MockMvcRequestBuilders.get("/user")
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.loginName").value("DEV-PC"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Override
    protected HelloController getController() {
        return helloController;
    }
}

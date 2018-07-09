package com.brian.springboot.controller;

import org.junit.After;
import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public abstract class AbstractControllerTest<T> {
    protected MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.standaloneSetup(getController()).build();
    }

    protected abstract T getController();

    @After
    public void cleanUp(){

    }
}

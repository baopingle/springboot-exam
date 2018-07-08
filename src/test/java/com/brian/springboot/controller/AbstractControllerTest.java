package com.brian.springboot.controller;

import com.brian.springboot.SpringbootApplication;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public abstract class AbstractControllerTest<T> {
    protected MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.standaloneSetup(getContoller()).build();
    }

    protected abstract T getContoller();
}

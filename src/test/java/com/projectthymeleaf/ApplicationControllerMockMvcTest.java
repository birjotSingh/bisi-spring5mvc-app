package com.projectthymeleaf;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



//@WebMvcTest
public class ApplicationControllerMockMvcTest {


    /*@Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;*/

    /*@Before
    public void setup(){
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    @Test
    public void testMyController() throws Exception{
        ResultMatcher resultMatcher = MockMvcResultMatchers.status().isOk();

        *//*MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/index")
                .contentType(MediaType.APPLICATION_JSON).content("{}");*//*

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/index/view/1");
        this.mockMvc.perform(builder);


    }*/

}

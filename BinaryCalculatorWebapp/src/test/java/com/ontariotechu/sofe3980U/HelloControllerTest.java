package com.ontariotechu.sofe3980U;

import org.junit.Test; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/hello"))
            .andExpect(status().isOk())
            .andExpect(view().name("hello"))
            .andExpect(model().attribute("name", "World"));
    }

    @Test
    public void helloWithName() throws Exception {
        this.mvc.perform(get("/hello?name=Doe"))
            .andExpect(status().isOk())
            .andExpect(view().name("hello"))
            .andExpect(model().attribute("name", "Doe"));
    }
}
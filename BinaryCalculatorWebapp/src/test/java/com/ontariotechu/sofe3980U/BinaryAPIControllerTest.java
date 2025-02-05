package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "111").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }

    @Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "111").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value("111"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value("1010"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("10001"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    @Test
    public void testMultiply() throws Exception {
        this.mvc.perform(post("/").param("operand1", "10").param("operator", "*").param("operand2", "11"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "110"));
    }

    @Test
    public void testAnd() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1010").param("operator", "&").param("operand2", "1100"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1000"));
    }

    @Test
    public void testOr() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1010").param("operator", "|").param("operand2", "1100"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1110"));
    }
}
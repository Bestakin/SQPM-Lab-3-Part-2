package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    // Addition tests
    @Test
    public void addPlain() throws Exception {
        mvc.perform(get("/add").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("10001"));
    }

    @Test
    public void addJson() throws Exception {
        mvc.perform(get("/add_json").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())
                // Adjust the expected JSON values as numbers or strings depending on your BinaryAPIResult implementation.
                .andExpect(jsonPath("$.operand1").value("111"))
                .andExpect(jsonPath("$.operand2").value("1010"))
                .andExpect(jsonPath("$.result").value("10001"))
                .andExpect(jsonPath("$.operator").value("add"));
    }

    // Multiplication tests
    @Test
    public void multiplyPlain() throws Exception {
        mvc.perform(get("/multiply").param("operand1", "10").param("operand2", "11"))
                .andExpect(status().isOk())
                .andExpect(content().string("110"));
    }

    @Test
    public void multiplyJson() throws Exception {
        mvc.perform(get("/multiply_json").param("operand1", "10").param("operand2", "11"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operand1").value("10"))
                .andExpect(jsonPath("$.operand2").value("11"))
                .andExpect(jsonPath("$.result").value("110"))
                .andExpect(jsonPath("$.operator").value("multiply"));
    }

    // Bitwise AND tests
    @Test
    public void andPlain() throws Exception {
        mvc.perform(get("/and").param("operand1", "1010").param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(content().string("1000"));
    }

    @Test
    public void andJson() throws Exception {
        mvc.perform(get("/and_json").param("operand1", "1010").param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operand1").value("1010"))
                .andExpect(jsonPath("$.operand2").value("1100"))
                .andExpect(jsonPath("$.result").value("1000"))
                .andExpect(jsonPath("$.operator").value("and"));
    }

    // Bitwise inclusive OR tests
    @Test
    public void orPlain() throws Exception {
        mvc.perform(get("/or").param("operand1", "1010").param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(content().string("1110"));
    }

    @Test
    public void orJson() throws Exception {
        mvc.perform(get("/or_json").param("operand1", "1010").param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operand1").value("1010"))
                .andExpect(jsonPath("$.operand2").value("1100"))
                .andExpect(jsonPath("$.result").value("1110"))
                .andExpect(jsonPath("$.operator").value("or"));
    }
}

package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getDefault() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("operand1", ""))
                .andExpect(model().attribute("operand1Focused", false));
    }

    @Test
    public void getParameter() throws Exception {
        mvc.perform(get("/").param("operand1", "111"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("operand1", "111"))
                .andExpect(model().attribute("operand1Focused", true));
    }

    @Test
    public void postAddition() throws Exception {
        mvc.perform(post("/")
                        .param("operand1", "111")
                        .param("operator", "+")
                        .param("operand2", "111"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1110"))
                .andExpect(model().attribute("operand1", "111"));
    }

    @Test
    public void postMultiply() throws Exception {
        mvc.perform(post("/")
                        .param("operand1", "10")
                        .param("operator", "*")
                        .param("operand2", "11"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                // 10 (binary "10") = 2, 11 = 3, product = 6 which is "110" in binary.
                .andExpect(model().attribute("result", "110"))
                .andExpect(model().attribute("operand1", "10"));
    }

    @Test
    public void postAnd() throws Exception {
        mvc.perform(post("/")
                        .param("operand1", "1010")
                        .param("operator", "&")
                        .param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                // 1010 AND 1100 = 1000
                .andExpect(model().attribute("result", "1000"))
                .andExpect(model().attribute("operand1", "1010"));
    }

    @Test
    public void postOr() throws Exception {
        this.mvc.perform(post("/")
                        .param("operand1", "1010")
                        .param("operator", "|")
                        .param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1110"))
                .andExpect(model().attribute("operand1", "1010"));
    }

}

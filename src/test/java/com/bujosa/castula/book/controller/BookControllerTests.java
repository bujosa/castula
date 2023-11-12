package com.bujosa.castula.book.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                .contentType("application/json")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"));
    }

}

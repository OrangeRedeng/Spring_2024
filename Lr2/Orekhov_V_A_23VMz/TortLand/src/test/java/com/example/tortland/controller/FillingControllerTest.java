package com.example.tortland.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.tortland.CommonTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class FillingControllerTest extends CommonTest {

    @Order(1)
    @Test
    void updateFilling() throws Exception {
        prepare("TestLoginConfectioner", "TestPasswordConfectioner");
        mvc.perform(post("/fillings/update")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id", String.valueOf(6L))
                        .param("cake", String.valueOf(1L))
                        .param("name", "TestNameFillingUpdate")
                        .param("pricePer", "10")
                        .param("createdBy", "TestLoginConfectioner")
                        .param("createdWhen", "2023-02-20"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(2)
    @Test
    void fillingAdd() throws Exception {
        prepare("TestLoginConfectioner", "TestPasswordConfectioner");
        mvc.perform(post("/fillings/add/1")
                        .param("cake", String.valueOf(1L))
                        .param("name", "TestNameFillingAdd")
                        .param("pricePer", "999"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());

    }
}

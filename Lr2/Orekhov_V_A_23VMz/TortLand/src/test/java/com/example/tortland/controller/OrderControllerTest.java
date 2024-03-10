package com.example.tortland.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.tortland.CommonTest;
import org.junit.jupiter.api.Order;
import com.example.tortland.model.City;
import com.example.tortland.model.Status;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class OrderControllerTest extends CommonTest {

    @Order(1)
    @Test
    void getAllOrders() throws Exception {
        prepare("admin", "admin");
        mvc.perform(get("/orders"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(2)
    @Test
    void getMyOrderClose() throws Exception {
        prepare("TestLogin", "TestPassword");
        mvc.perform(get("/orders/myOrderClose"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(3)
    @Test
    void getDetailed() throws Exception {
        prepare("TestLogin", "TestPassword");
        mvc.perform(get("/orders/detailed/1")
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(4)
    @Test
    void detailedConfectioner() throws Exception {
        prepare("TestLoginConfectioner", "TestPasswordConfectioner");
        mvc.perform(post("/orders/detailed")
                        .param("id", String.valueOf(1L))
                        .param("status", String.valueOf(Status.COOKING))
                        .param("deliveryDate", "2000-01-01")
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(5)
    @Test
    void updateOrder() throws Exception {
        prepare("admin", "admin");
        mvc.perform(post("/orders/update")
                        .param("id", String.valueOf(1L))
                        .param("users", String.valueOf(1L))
                        .param("userConfectioners", String.valueOf(2L))
                        .param("cake", String.valueOf(1L))
                        .param("filling", String.valueOf(1L))
                        .param("deliveryDate", "2000-01-01")
                        .param("status", String.valueOf(Status.EXPECTATION))
                        .param("title", "Обычный")
                        .param("number", "TL1008TTo")
                        .param("activity", String.valueOf(true))
                        .param("pickup", String.valueOf(true))
                        .param("address", "testOrderUpdate")
                        .param("city", String.valueOf(City.NIZHNIY_NOVGOROD))
                        .param("weightFrom", "5")
                        .param("price", "2000")
                        .param("createdBy", "TestLogin")
                        .param("createdWhen", "2023-02-20"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(6)
    @Test
    void deliveredOrder() throws Exception{
        prepare("TestLoginConfectioner", "TestPasswordConfectioner");
        mvc.perform(get("/orders/delivered/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(7)
    @Test
    void refundOrder() throws Exception {
        prepare("admin", "admin");
        mvc.perform(post("/orders/update")
                        .param("id", String.valueOf(1L))
                        .param("users", String.valueOf(1L))
                        .param("userConfectioners", String.valueOf(2L))
                        .param("cake", String.valueOf(1L))
                        .param("filling", String.valueOf(1L))
                        .param("deliveryDate", "2000-01-01")
                        .param("status", String.valueOf(Status.EXPECTATION))
                        .param("title", "Обычный")
                        .param("number", "TL1008TTo")
                        .param("activity", String.valueOf(true))
                        .param("pickup", String.valueOf(true))
                        .param("address", "testOrder")
                        .param("city", String.valueOf(City.NIZHNIY_NOVGOROD))
                        .param("weightFrom", "5")
                        .param("price", "2000")
                        .param("createdBy", "TestLogin")
                        .param("createdWhen", "2023-02-20"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(8)
    @Test
    void creatOrder() throws Exception {
        prepare("TestLogin", "TestPassword");
        mvc.perform(post("/orders/create/1")
                        .param("users", String.valueOf(1L))
                        .param("userConfectioners", String.valueOf(2L))
                        .param("cake", String.valueOf(1L))
                        .param("filling", String.valueOf(1L))
                        .param("deliveryDate", "2000-01-01")
                        .param("status", String.valueOf(Status.EXPECTATION))
                        .param("title", "Обычный")
                        .param("number", "testOrder")
                        .param("activity", String.valueOf(true))
                        .param("pickup", String.valueOf(true))
                        .param("address", "testOrder")
                        .param("city", String.valueOf(City.NIZHNIY_NOVGOROD))
                        .param("weightFrom", "5")
                        .param("price", "1000"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

}

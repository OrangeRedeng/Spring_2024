package com.example.tortland.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.tortland.CommonTest;
import com.example.tortland.model.City;
import com.example.tortland.model.Form;
import com.example.tortland.model.Status;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class CustomOrderControllerTest extends CommonTest {

    @Order(1)
    @Test
    void getAllCustomOrders() throws Exception {
        prepare("admin", "admin");
        mvc.perform(get("/customOrders"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(2)
    @Test
    void getAllMyCustomOrders() throws Exception {
        prepare("TestLogin", "TestPassword");
        mvc.perform(get("/customOrders/myCustomOrder"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(3)
    @Test
    void getAllMyCustomOrdersClose() throws Exception {
        prepare("TestLogin", "TestPassword");
        mvc.perform(get("/customOrders/myCustomOrderClose"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(4)
    @Test
    void getDetailed() throws Exception {
        prepare("TestLogin", "TestPassword");
        mvc.perform(get("/customOrders/detailed/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(5)
    @Test
    void detailedConfectioner() throws Exception {
        prepare("TestLoginConfectioner", "TestPasswordConfectioner");
        mvc.perform(post("/customOrders/detailed")
                        .param("id", String.valueOf(1L))
                        .param("status", String.valueOf(Status.COOKING))
                        .param("price", "1000")
                        .param("deliveryDate", "2000-01-01"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(6)
    @Test
    void updateCustomOrder() throws Exception {
        prepare("admin", "admin");
        mvc.perform(post("/customOrders/update")
                        .param("id", String.valueOf(1L))
                        .param("users", String.valueOf(1L))
                        .param("userConfectioners", String.valueOf(2L))
                        .param("tiers", "1")
                        .param("title", "Личный")
                        .param("wT", "2")
                        .param("form", String.valueOf(Form.CLASSIC))
                        .param("decoration", "customOrderDecorationTestUpdate")
                        .param("activity", String.valueOf(true))
                        .param("filling", "customOrderFillingTestUpdate")
                        .param("shortDescription", "customOrderShortDescriptionTestUpdate")
                        .param("number", "customOrderNumberTestUpdate")
                        .param("city", String.valueOf(City.NIZHNIY_NOVGOROD))
                        .param("address", "customOrderAddressTestUpdate")
                        .param("status", String.valueOf(Status.EXPECTATION))
                        .param("price", "1000")
                        .param("deliveryDate", "2000-01-01")
                        .param("createdBy", "TestLogin")
                        .param("createdWhen", "2023-02-20"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(7)
    @Test
    void deliveredCustomOrder() throws Exception{
        prepare("TestLoginConfectioner", "TestPasswordConfectioner");
        mvc.perform(get("/customOrders/delivered/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(8)
    @Test
    void refundCustomOrder() throws Exception {
        prepare("admin", "admin");
        mvc.perform(post("/customOrders/update")
                        .param("id", String.valueOf(1L))
                        .param("users", String.valueOf(1L))
                        .param("userConfectioners", String.valueOf(2L))
                        .param("tiers", "1")
                        .param("title", "Личный")
                        .param("wT", "2")
                        .param("form", String.valueOf(Form.CLASSIC))
                        .param("decoration", "customOrderDecorationTest")
                        .param("activity", String.valueOf(true))
                        .param("filling", "customOrderFillingTest")
                        .param("shortDescription", "customOrderShortDescriptionTest")
                        .param("number", "customOrderNumberTest")
                        .param("city", String.valueOf(City.NIZHNIY_NOVGOROD))
                        .param("address", "customOrderAddressTest")
                        .param("status", String.valueOf(Status.EXPECTATION))
                        .param("price", (String) null)
                        .param("deliveryDate", "2000-01-01")
                        .param("createdBy", "TestLogin")
                        .param("createdWhen", "2023-02-20"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(9)
    @Test
    void creatCustomOrder() throws Exception {
        prepare("TestLogin", "TestPassword");
        mvc.perform(post("/customOrders/create/2")
                        .param("users", String.valueOf(1L))
                        .param("userConfectioners", String.valueOf(2L))
                        .param("tiers", "1")
                        .param("title", "Личный")
                        .param("wT", "2")
                        .param("form", String.valueOf(Form.CLASSIC))
                        .param("decoration", "customOrderDecorationTest")
                        .param("activity", String.valueOf(true))
                        .param("filling", "customOrderFillingTest")
                        .param("shortDescription", "customOrderShortDescriptionTest")
                        .param("number", "customOrderNumberTest")
                        .param("city", String.valueOf(City.NIZHNIY_NOVGOROD))
                        .param("address", "customOrderAddressTest")
                        .param("status", String.valueOf(Status.EXPECTATION))
                        .param("price", (String) null)
                        .param("deliveryDate", "2000-01-01"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

}

package com.example.tortland.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.tortland.CommonTest;
import com.example.tortland.model.City;
import com.example.tortland.model.Form;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class CakeControllerTest extends CommonTest {

    @Order(1)
    @Test
    void getCakeList() throws Exception {
        prepare("TestLogin", "TestPassword");
        mvc.perform(get("/cakes"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(2)
    @Test
    void getCakeInfo() throws Exception {
        prepare("TestLogin", "TestPassword");
        mvc.perform(get("/cakes/info/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(3)
    @Test
    void getCakeConfectioner() throws Exception {
        prepare("TestLogin", "TestPassword");
        mvc.perform(get("/cakes/confectioners/2"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(4)
    @Test
    void updateCake() throws Exception {
        prepare("TestLoginConfectioner", "TestPasswordConfectioner");
        MockHttpServletRequestBuilder multipart = multipart("/cakes/update")
                .file("file1", "123".getBytes())
                .file("file2", "123".getBytes())
                .file("file3", "123".getBytes())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("id", String.valueOf(1L))
                .param("cookingTime", "10")
                .param("name", "TestUpdateCake")
                .param("decorating", "TestUpdateCake")
                .param("weightFrom", "5")
                .param("shortDescription", "TestUpdateCake")
                .param("city", String.valueOf(City.NIZHNIY_NOVGOROD))
                .param("form", String.valueOf(Form.CLASSIC))
                .param("user", String.valueOf(2L))
                .param("createdBy", "TestLoginConfectioner")
                .param("createdWhen", "2023-02-20")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        this.mvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(6)
    @Test
    void deleteCake() throws Exception {
        prepare("TestLoginConfectioner", "TestPasswordConfectioner");
        mvc.perform(get("/cakes/delete/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(5)
    @Test
    void deleteImageCake() throws Exception {
        prepare("admin", "admin");
        mvc.perform(get("/cakes/deleteImage/1/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(7)
    @Test
    void recoveryCake() throws Exception {
        prepare("admin", "admin");
        mvc.perform(get("/cakes/unblock/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(8)
    @Test
    void createCake() throws Exception {
        prepare("TestLoginConfectioner", "TestPasswordConfectioner");
        MockHttpServletRequestBuilder multipart = multipart("/cakes/add")
                .file("file1", "123".getBytes())
                .file("file2", "123".getBytes())
                .file("file3", "123".getBytes())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("cookingTime", "10")
                .param("name", "TestAddCake")
                .param("decorating", "TestAddCake")
                .param("weightFrom", "5")
                .param("shortDescription", "TestAddCake")
                .param("city", String.valueOf(City.NIZHNIY_NOVGOROD))
                .param("form", String.valueOf(Form.CLASSIC))
                .accept(MediaType.APPLICATION_JSON_VALUE);
        this.mvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection());

    }

    @Order(9)
    @Test
    void searchCake() throws Exception {
        prepare("TestLoginConfectioner", "TestPasswordConfectioner");
        mvc.perform(get("/cakes/search?keyword=TestFilling"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}

package com.example.tortland.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.tortland.CommonTest;
import com.example.tortland.model.City;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class UserControllerTest extends CommonTest {

    @Order(1)
    @Test
    void getAllConfectionerUser() throws Exception {
        prepare("TestLogin", "TestPassword");
        mvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(2)
    @Test
    void getAllConfectionerAdmin() throws Exception {
        prepare("admin", "admin");
        mvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(3)
    @Test
    void getAllUsersAdmin() throws Exception {
        prepare("admin", "admin");
        mvc.perform(get("/users/allUsers"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(4)
    @Test
    void getAllAdmin() throws Exception {
        prepare("admin", "admin");
        mvc.perform(get("/users/all"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(5)
    @Test
    void getDetailedInformation() throws Exception {
        prepare("admin", "admin");
        mvc.perform(get("/users/detailedInformation/2"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(6)
    @Test
    void getProfileConfectioner() throws Exception {
        prepare("TestLogin", "TestPassword");
        mvc.perform(get("/users/profileConfectioner/2"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(7)
    @Test
    void getProfile() throws Exception {
        prepare("TestLogin", "TestPassword");
        mvc.perform(get("/users/profile/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(16)
    @Test
    void creatUser() throws Exception {
        mvc.perform(post("/users/registration")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("login", "TestLoginRegistration" + getRandomNumber(1, 10000))
                        .param("password", "TestPasswordRegistration")
                        .param("email", "TestEmailRegistration" + getRandomNumber(1, 10000) + "@mail.ru")
                        .param("birthDate", "2000-01-01")
                        .param("firstName", "TestFirstNameRegistration")
                        .param("lastName", "TestLastNameRegistration")
                        .param("middleName", "TestMiddleNameRegistration")
                        .param("phone", "TestPhoneRegistration")
                        .param("address", "TestAddressRegistration")
                        .param("city", String.valueOf(City.NIZHNIY_NOVGOROD))
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(9)
    @Test
    void updateUser() throws Exception {
        prepare("TestLogin", "TestPassword");
        mvc.perform(post("/users/update")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id", String.valueOf(1L))
                        .param("role", String.valueOf(1L))
                        .param("status", "Пользователь")
                        .param("login", "TestLogin")
                        .param("password", "$2a$10$bY1tY9gtAekJ.7pPo5UkgONFpQzvJs7j/gIVQSAYigu9GXoybiRvu")
                        .param("email", "TestEmail@mail.ru")
                        .param("birthDate", "2000-01-01")
                        .param("firstName", "TestFirstNameUpdate")
                        .param("lastName", "TestLastNameUpdate")
                        .param("middleName", "TestMiddleNameUpdate")
                        .param("phone", "TestPhoneUpdate")
                        .param("city", String.valueOf(City.NIZHNIY_NOVGOROD))
                        .param("address", "TestAddressUpdate")
                        .param("plug", "/images/plug.jpeg")
                        .param("createdBy", "REGISTRATION")
                        .param("createdWhen", "2023-02-20")
                        .param("updatedBy", "TestLogin")
                        .param("avatar", "id")
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(8)
    @Test
    void updateImage() throws Exception {
        prepare("TestLogin", "TestPassword");
        MockHttpServletRequestBuilder multipart = multipart("/users/updateImages")
                .file("file", "123".getBytes())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("id", String.valueOf(1L))
                .accept(MediaType.APPLICATION_JSON_VALUE);
        this.mvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(10)
    @Test
    void deleteImage() throws Exception {
        prepare("admin", "admin");
        mvc.perform(get("/users/deleteImage/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(11)
    @Test
    void delete() throws Exception {
        prepare("admin", "admin");
        mvc.perform(get("/users/delete/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(12)
    @Test
    void unblock() throws Exception {
        prepare("admin", "admin");
        mvc.perform(get("/users/unblock/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(13)
    @Test
    void applicationCreate() throws Exception {
        prepare("TestLogin", "TestPassword");
        mvc.perform(post("/users/application")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id", String.valueOf(1L))
                        .param("experience", "experienceTest")
                        .param("aboutMe", "aboutMeTest"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Order(14)
    @Test
    void getApplication() throws Exception {
        prepare("admin", "admin");
        mvc.perform(get("/users/get-application"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Order(15)
    @Test
    void rejectApplication() throws Exception {
        prepare("admin", "admin");
        mvc.perform(get("/users/rejectApplication/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

}

package com.example.springsocial.controller;

import com.example.springsocial.payload.LowInventoryRequest;
import com.example.springsocial.payload.NotificationAboutToExpireRequest;
import com.example.springsocial.payload.NotificationExpiredRequest;
import com.example.springsocial.repository.InventoryRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NotificationController.class)
@WebAppConfiguration
@EnableWebMvc
public class NotificationControllerTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    public String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    public <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @MockBean
    InventoryRepository inventoryRepository;

    @Test
    void expiredItemsWhenValidInput_thenReturnSuccess() throws Exception {
        String uri = "/findExpiredInventory";
        NotificationExpiredRequest notificationExpiredRequest = new NotificationExpiredRequest();
        notificationExpiredRequest.setRestaurant_id("r10");

        String requestBodyJson = new Gson().toJson(notificationExpiredRequest);

        System.out.println(mockMvc);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(requestBodyJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void aboutToExpireItemsWhenValidInput_thenReturnSuccess() throws Exception {
        String uri = "/findAboutToExpireInventory";
        NotificationAboutToExpireRequest notificationAboutToExpireRequest = new NotificationAboutToExpireRequest();
        notificationAboutToExpireRequest.setRestaurant_id("r10");
        notificationAboutToExpireRequest.setExpires_in_days(5);

        String requestBodyJson = new Gson().toJson(notificationAboutToExpireRequest);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(requestBodyJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void lowInventoryItemsWhenValidInput_thenReturnSuccess() throws Exception {
        String uri = "/findLowQuantityInventoryItems";
        LowInventoryRequest lowInventoryRequest = new LowInventoryRequest();
        lowInventoryRequest.setRestaurant_id("r10");
        lowInventoryRequest.setMax_qty(5);

        String requestBodyJson = new Gson().toJson(lowInventoryRequest);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(requestBodyJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

}

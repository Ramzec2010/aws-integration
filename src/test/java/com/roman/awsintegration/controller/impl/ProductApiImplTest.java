package com.roman.awsintegration.controller.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roman.awsintegration.AwsIntegrationApplicationTests;
import com.roman.awsintegration.exception.handler.AppExceptionHandler;
import com.roman.awsintegration.rest.request.ProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

class ProductApiImplTest extends AwsIntegrationApplicationTests {

    private static final String VALID_PRODUCT_ID = "1";
    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @Autowired
    private ProductApiImpl productApi;


    @BeforeEach
    public void setup() {
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JsonNullableModule());
        this.mockMvc = MockMvcBuilders.standaloneSetup(productApi)
                .setControllerAdvice(new AppExceptionHandler())
                .build();
    }

    @Test
    public void shouldGetExistingProduct() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/product/{productId}", VALID_PRODUCT_ID)
                        .header(CONTENT_TYPE, "application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Get"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(100));
    }

    @Test
    public void shouldUpdateExistingProduct() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/v1/product/{productId}", "2")
                        .header(CONTENT_TYPE, "application/json").content(mapper
                        .writeValueAsString(ProductRequest
                                .builder()
                                .name("New_updated")
                                .build())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New_updated"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(200));
    }

    @Test
    public void shouldDeleteExistingProduct() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/v1/product/{productId}", "2")
                        .header(CONTENT_TYPE, "application/json").content(mapper
                        .writeValueAsString(ProductRequest
                                .builder()
                                .name("New_updated")
                                .build())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New_updated"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(200));
    }
}
package com.roman.awsintegration.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roman.awsintegration.AwsIntegrationApplicationTests;
import com.roman.awsintegration.exception.CategoryNotFoundException;
import com.roman.awsintegration.exception.handler.AppExceptionHandler;
import com.roman.awsintegration.rest.request.CategoryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

class CategoryApiImplTest extends AwsIntegrationApplicationTests {

    private static final String VALID_CATEGORY_ID = "1";

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @Autowired
    private CategoryApiImpl categoryApi;


    @BeforeEach
    public void setup() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JsonNullableModule());
        this.mockMvc = MockMvcBuilders.standaloneSetup(categoryApi)
                .setControllerAdvice(new AppExceptionHandler())
                .build();
    }

    @Test
    public void shouldGetExistingCategory() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/category/{categoryId}", VALID_CATEGORY_ID)
                        .header(CONTENT_TYPE, "application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Get"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Get category"));
    }

    @Test
    public void shouldUpdateExistingCategory() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/v1/category/{categoryId}", "2")
                        .header(CONTENT_TYPE, "application/json").content(mapper
                        .writeValueAsString(CategoryRequest
                                .builder()
                                .name("New_updated_category")
                                .build())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New_updated_category"));
    }

    @Test
    public void shouldDeleteExistingCategory() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/category/{categoryId}", "3")
                        .header(CONTENT_TYPE, "application/json"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        assertThrows(CategoryNotFoundException.class, () -> categoryApi.getCategory(3L));
    }
}
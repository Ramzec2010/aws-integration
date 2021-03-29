package com.roman.awsintegration.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roman.awsintegration.AwsIntegrationApplicationTests;
import com.roman.awsintegration.exception.handler.AppExceptionHandler;
import com.roman.awsintegration.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

class CategoryProductsApiTest extends AwsIntegrationApplicationTests {

    private static final String VALID_CATEGORY_ID = "1";
    private static final String VALID_PRODUCT_ID = "1";
    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @Autowired
    private CategoryProductsApi categoryProductsApi;

    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JsonNullableModule());
        this.mockMvc = MockMvcBuilders.standaloneSetup(categoryProductsApi)
                .setControllerAdvice(new AppExceptionHandler())
                .build();
    }

    @Test
    public void shouldAssignProductToCategory() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/categories/1/products/1")
                        .header(CONTENT_TYPE, "application/json"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        assertEquals(1, categoryService.getCategory(Long.valueOf(VALID_CATEGORY_ID)).getProducts().size());
    }

}
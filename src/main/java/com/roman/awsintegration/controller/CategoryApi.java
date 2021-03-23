package com.roman.awsintegration.controller;

import com.roman.awsintegration.rest.request.CategoryRequest;
import com.roman.awsintegration.rest.response.CategoryResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface CategoryApi {
    @RequestMapping(value = "/api/v1/category/{categoryId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCategory(@PathVariable(name = "categoryId", required = true) Long categoryId);

    @RequestMapping(value = "/api/v1/category/{categoryId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<CategoryResponse> getCategory(@PathVariable Long categoryId) throws Exception;

    @RequestMapping(value = "/api/v1/category",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request);

    @RequestMapping(value = "/api/v1/category/{categoryId}",
            produces = {"application/json"},
            method = RequestMethod.PATCH)
    ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequest request);

    @RequestMapping(value = "/api/v1/categories",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<CategoryResponse>> getAllCategories(Pageable pageable);
}

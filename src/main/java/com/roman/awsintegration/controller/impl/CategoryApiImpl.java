package com.roman.awsintegration.controller.impl;

import com.roman.awsintegration.controller.CategoryApi;
import com.roman.awsintegration.rest.request.CategoryRequest;
import com.roman.awsintegration.rest.response.CategoryResponse;
import com.roman.awsintegration.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryApiImpl implements CategoryApi {
    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseEntity<Void> deleteCategory(Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CategoryResponse> getCategory(Long categoryId) throws Exception {
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    @Override
    public ResponseEntity<CategoryResponse> createCategory(CategoryRequest request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @Override
    public ResponseEntity<CategoryResponse> updateCategory(Long categoryId, CategoryRequest request) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, request));
    }

    @Override
    public ResponseEntity<List<CategoryResponse>> getAllCategories(Pageable pageable) {
        return ResponseEntity.ok(categoryService.getAllCategory(pageable));
    }
}

package com.roman.awsintegration.services;

import com.roman.awsintegration.rest.request.CategoryRequest;
import com.roman.awsintegration.rest.response.CategoryResponse;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CategoryService {
    void deleteCategory(Long categoryId);

    CategoryResponse getCategory(Long categoryId);

    CategoryResponse createCategory(CategoryRequest request);

    CategoryResponse updateCategory(Long categoryId, CategoryRequest request);

    List<CategoryResponse> getAllCategory(Pageable pageable);

    CategoryResponse getCategoryWithFilteredProducts(Long categoryid, BigDecimal minPrice, BigDecimal maxPrice, String like);
}

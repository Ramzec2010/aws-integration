package com.roman.awsintegration.services.impl;

import com.roman.awsintegration.converter.Converter;
import com.roman.awsintegration.exception.CategoryNotFoundException;
import com.roman.awsintegration.model.CategoryEntity;
import com.roman.awsintegration.model.ProductEntity;
import com.roman.awsintegration.repository.CategoryRepository;
import com.roman.awsintegration.rest.request.CategoryRequest;
import com.roman.awsintegration.rest.response.CategoryResponse;
import com.roman.awsintegration.rest.response.ProductResponse;
import com.roman.awsintegration.services.CategoryService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Converter converter;

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    @SneakyThrows
    public CategoryResponse getCategory(Long categoryId) {
        CategoryEntity bycategory = categoryRepository
                .findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);

        return converter.mapCategoryModelToDto(bycategory);
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        CategoryEntity entity = CategoryEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        CategoryEntity savedCategory = categoryRepository.save(entity);
        return converter.mapCategoryModelToDto(savedCategory);
    }


    @Override
    @SneakyThrows
    public CategoryResponse updateCategory(Long categoryId, CategoryRequest request) {
        CategoryEntity category = categoryRepository
                .findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);
        if (Objects.nonNull(request.getDescription())) {
            category.setDescription(request.getDescription());
        }
        if (Objects.nonNull(request.getName())) {
            category.setName(request.getName());
        }
        category = categoryRepository.save(category);
        return converter.mapCategoryModelToDto(category);
    }

    @Override
    public List<CategoryResponse> getAllCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(converter::mapCategoryModelToDto)
                .collect(Collectors.toList());

    }

    @Override
    @SneakyThrows
    public CategoryResponse getCategoryWithFilteredProducts(Long categoryid, BigDecimal minPrice, BigDecimal maxPrice, String like) {
        CategoryEntity category = categoryRepository
                .findByCategoryIdAndProductsPriceBetweenAndProductsNameContaining(categoryid, minPrice, maxPrice, like)
                .orElseThrow(CategoryNotFoundException::new);
        return converter.mapCategoryModelToDto(category);
    }

    private List<ProductResponse> buildProductResponse(Set<ProductEntity> products) {
        return products.stream().map(converter::mapProductEntityToDto).collect(Collectors.toList());
    }
}

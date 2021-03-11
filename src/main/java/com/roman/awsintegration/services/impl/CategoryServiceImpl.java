package com.roman.awsintegration.services.impl;

import com.roman.awsintegration.model.CategoryEntity;
import com.roman.awsintegration.repository.CategoryRepository;
import com.roman.awsintegration.rest.request.CategoryRequest;
import com.roman.awsintegration.rest.response.CategoryResponse;
import com.roman.awsintegration.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryResponse getCategory(Long categoryId) {
        CategoryEntity byId = categoryRepository.findById(categoryId).get();
        return mapCategoryModelToDto(byId);
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        CategoryEntity entity = CategoryEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        CategoryEntity save = categoryRepository.save(entity);
        return mapCategoryModelToDto(save);
    }

    private CategoryResponse mapCategoryModelToDto(CategoryEntity save) {
        return CategoryResponse.builder()
                .name(save.getName())
                .id(save.getCategoryId())
                .description(save.getDescription())
                .build();
    }

    @Override
    public CategoryResponse updateCategory(Long categoryId, CategoryRequest request) {
        CategoryEntity entity = categoryRepository.findById(categoryId).get();
        if (Objects.nonNull(request.getDescription())) {
            entity.setDescription(request.getDescription());
        }
        if (Objects.nonNull(request.getName())) {
            entity.setName(request.getName());
        }
        entity = categoryRepository.save(entity);
        return mapCategoryModelToDto(entity);
    }
}

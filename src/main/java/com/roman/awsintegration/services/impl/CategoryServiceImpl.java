package com.roman.awsintegration.services.impl;

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

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    @SneakyThrows
    public CategoryResponse getCategory(Long categoryId) {
        CategoryEntity byId = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);

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
                .numberOfProducts(save.getNumberOfProducts())
                .products(save.getProducts().stream().map(this::mapProductEntityToDto).collect(Collectors.toList()))
                .build();
    }

    private ProductResponse mapProductEntityToDto(ProductEntity productEntity) {
        return ProductResponse.builder()
                .name(productEntity.getName())
                .id(productEntity.getProductId())
                .price(productEntity.getPrice())
                .categories(productEntity.getCategories().stream().map(CategoryEntity::getName).collect(Collectors.toList()))
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

    @Override
    public List<CategoryResponse> getAllCategory(Pageable pageable) {
       return categoryRepository.findAll(pageable).stream().map(entity -> CategoryResponse.builder()
                .id(entity.getCategoryId())
                .name(entity.getName())
                .description(entity.getDescription())
                .products(buildProductResponse(entity.getProducts()))
                .build()).collect(Collectors.toList());

    }

    private List<ProductResponse> buildProductResponse(Set<ProductEntity> products) {
        return products.stream().map(this::mapProductEntityToDto).collect(Collectors.toList());
    }
}

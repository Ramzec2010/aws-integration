package com.roman.awsintegration.converter;

import com.roman.awsintegration.model.CategoryEntity;
import com.roman.awsintegration.model.ProductEntity;
import com.roman.awsintegration.rest.response.CategoryResponse;
import com.roman.awsintegration.rest.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Converter {

    public CategoryResponse mapCategoryModelToDto(CategoryEntity save) {
        return CategoryResponse.builder()
                .name(save.getName())
                .id(save.getCategoryId())
                .description(save.getDescription())
                .numberOfProducts(save.getNumberOfProducts())
                .products(save.getProducts().stream().map(this::mapProductEntityToDto).collect(Collectors.toList()))
                .build();
    }

    public ProductResponse mapProductEntityToDto(ProductEntity productEntity) {
        return ProductResponse.builder()
                .name(productEntity.getName())
                .id(productEntity.getProductId())
                .price(productEntity.getPrice())
                .categories(productEntity.getCategories().stream().map(CategoryEntity::getName).collect(Collectors.toList()))
                .build();
    }
}

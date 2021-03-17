package com.roman.awsintegration.services.impl;

import com.roman.awsintegration.exception.CategoryNotFoundException;
import com.roman.awsintegration.exception.ProductNotFoundException;
import com.roman.awsintegration.model.CategoryEntity;
import com.roman.awsintegration.model.ProductEntity;
import com.roman.awsintegration.repository.CategoryRepository;
import com.roman.awsintegration.repository.ProductRepository;
import com.roman.awsintegration.services.CategoryProductService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryProductServiceImpl implements CategoryProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    @SneakyThrows
    public void addProductToCategory(Long categoryId, Long productId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        categoryEntity.getProducts().add(productEntity);
        productEntity.getCategories().add(categoryEntity);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void removeProductFromCategory(Long categoryid, Long productid) {
        Optional<CategoryEntity> category = categoryRepository.findByCategoryIdAndProductsProductId(categoryid, productid);

        if (category.isPresent()){
            Optional<ProductEntity> first = category.get().getProducts().stream().filter(productEntity -> productEntity.getProductId() == productid).findFirst();
            category.get().getProducts().remove(first);
            first.get().getCategories().remove(category.get());
            categoryRepository.save(category.get());
            productRepository.save(first.get());
        }
    }
}

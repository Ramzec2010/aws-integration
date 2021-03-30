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
        productRepository.save(productEntity);
    }

    @Override
    public void removeProductFromCategory(Long categoryid, Long productid) {
        Optional<CategoryEntity> category = categoryRepository.findByCategoryIdAndProductsProductId(categoryid, productid);

        if (category.isPresent()){
            ProductEntity relatedProduct = category.get().getProducts().stream().filter(productEntity -> productEntity.getProductId().equals(productid)).findFirst().get();
            category.get().getProducts().removeIf(entity -> entity.getProductId().equals(relatedProduct.getProductId()));
            relatedProduct.getCategories().removeIf(categoryEntity -> categoryEntity.getCategoryId().equals(category.get().getCategoryId()));
            categoryRepository.save(category.get());
            productRepository.save(relatedProduct);
        }
    }
}

package com.roman.awsintegration.repository;

import com.roman.awsintegration.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByCategoryIdAndProductsProductId(Long categoryId, Long productId);

    Optional<CategoryEntity> findByCategoryIdAndProductsPriceBetweenAndProductsNameContaining(Long categoryId, BigDecimal minVal, BigDecimal maxVal, String like);
}

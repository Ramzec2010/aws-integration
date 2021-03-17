package com.roman.awsintegration.repository;

import com.roman.awsintegration.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByCategoryIdAndProductsProductId(Long categoryId, Long productId);
}

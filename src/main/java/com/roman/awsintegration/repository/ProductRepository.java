package com.roman.awsintegration.repository;

import com.roman.awsintegration.model.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    Page<ProductEntity> findByPriceBetweenAndNameContaining(BigDecimal minVal, BigDecimal maxVal, String like, Pageable pageable);
}

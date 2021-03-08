package com.roman.awsintegration.repository;

import com.roman.awsintegration.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}

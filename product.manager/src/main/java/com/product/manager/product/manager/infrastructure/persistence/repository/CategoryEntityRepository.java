package com.product.manager.product.manager.infrastructure.persistence.repository;

import com.product.manager.product.manager.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);
}

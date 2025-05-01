package com.product.manager.product.manager.infrastructure.persistence.repository;

import com.product.manager.product.manager.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {}
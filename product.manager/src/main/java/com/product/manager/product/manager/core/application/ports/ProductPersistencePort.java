package com.product.manager.product.manager.core.application.ports;

import com.product.manager.product.manager.core.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductPersistencePort {
    Product saveProduct(Product product);
    Optional<Product> findProductById(Long id);
    List<Product> findAllProducts();
    void deleteProductById(Long id);
}
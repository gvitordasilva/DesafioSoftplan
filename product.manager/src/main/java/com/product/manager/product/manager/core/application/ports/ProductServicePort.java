package com.product.manager.product.manager.core.application.ports;

import com.product.manager.product.manager.core.dto.ProductRequestDTO;
import com.product.manager.product.manager.core.dto.ProductResponseDTO;

import java.util.List;

public interface ProductServicePort {
    ProductResponseDTO createProduct(ProductRequestDTO dto);
    ProductResponseDTO getProductById(Long id);
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto);
    void deleteProduct(Long id);
}
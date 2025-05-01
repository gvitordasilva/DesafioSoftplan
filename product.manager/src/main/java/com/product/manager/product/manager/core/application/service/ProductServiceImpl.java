package com.product.manager.product.manager.core.application.service;

import com.product.manager.product.manager.core.application.ports.CategoryPersistencePort;
import com.product.manager.product.manager.core.application.ports.ProductPersistencePort;
import com.product.manager.product.manager.core.application.ports.ProductServicePort;
import com.product.manager.product.manager.core.domain.exception.CategoryNotFoundException;
import com.product.manager.product.manager.core.domain.exception.ProductNotFoundException;
import com.product.manager.product.manager.core.domain.model.Category;
import com.product.manager.product.manager.core.domain.model.Product;
import com.product.manager.product.manager.core.dto.ProductRequestDTO;
import com.product.manager.product.manager.core.dto.ProductResponseDTO;
import com.product.manager.product.manager.infrastructure.persistence.mapper.ProductEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductServicePort {

    private final ProductPersistencePort persistencePort;
    private final ProductEntityMapper mapper;
    private final CategoryPersistencePort categoryPersistencePort;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        if (dto.getCategoryId() == null) {
            throw new IllegalArgumentException("ID da categoria é obrigatório");
        }

        Category category = categoryPersistencePort.findCategoryById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(dto.getCategoryId()));
        System.out.println("Categoria encontrada: " + category.getName());

        Product product = mapper.toDomain(dto);

        product.setCategory(category);

        Product savedProduct = persistencePort.saveProduct(product);
        return mapper.toDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        return persistencePort.findProductById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return persistencePort.findAllProducts().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        Product existingProduct = persistencePort.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existingProduct.setName(dto.getName());
        existingProduct.setDescription(dto.getDescription());
        existingProduct.setPrice(dto.getPrice());

        Product updatedProduct = persistencePort.saveProduct(existingProduct);
        return mapper.toDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (persistencePort.findProductById(id).isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        persistencePort.deleteProductById(id);
    }
}
package com.product.manager.product.manager.infrastructure.persistence.adapter;

import com.product.manager.product.manager.core.application.ports.ProductPersistencePort;
import com.product.manager.product.manager.core.domain.exception.CategoryNotFoundException;
import com.product.manager.product.manager.core.domain.model.Product;
import com.product.manager.product.manager.infrastructure.persistence.entity.CategoryEntity;
import com.product.manager.product.manager.infrastructure.persistence.entity.ProductEntity;
import com.product.manager.product.manager.infrastructure.persistence.mapper.ProductEntityMapper;
import com.product.manager.product.manager.infrastructure.persistence.repository.CategoryEntityRepository;
import com.product.manager.product.manager.infrastructure.persistence.repository.ProductEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductPersistencePort {

    private final ProductEntityRepository repository;
    private final ProductEntityMapper mapper;
    private final CategoryEntityRepository categoryRepository;

    @Override
    public Product saveProduct(Product product) {
        ProductEntity entity = mapper.toEntity(product);

        if (entity.getCategory() != null) {
            CategoryEntity category = categoryRepository.findById(entity.getCategory().getId())
                    .orElseThrow(() -> new CategoryNotFoundException(entity.getCategory().getId()));
            entity.setCategory(category);
        }

        ProductEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Product> findAllProducts() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }
}
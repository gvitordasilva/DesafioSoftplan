package com.product.manager.product.manager.infrastructure.persistence.adapter;

import com.product.manager.product.manager.core.application.ports.CategoryPersistencePort;
import com.product.manager.product.manager.core.domain.model.Category;
import com.product.manager.product.manager.infrastructure.persistence.entity.CategoryEntity;
import com.product.manager.product.manager.infrastructure.persistence.mapper.CategoryEntityMapper;
import com.product.manager.product.manager.infrastructure.persistence.repository.CategoryEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryJpaAdapter implements CategoryPersistencePort {

    private final CategoryEntityRepository repository;
    private final CategoryEntityMapper mapper;

    @Override
    public Category saveCategory(Category category) {
        CategoryEntity entity = mapper.toEntity(category);
        CategoryEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Category> findAllCategories() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteCategoryById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
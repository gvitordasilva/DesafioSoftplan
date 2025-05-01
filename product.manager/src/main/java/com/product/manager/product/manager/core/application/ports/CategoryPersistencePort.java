package com.product.manager.product.manager.core.application.ports;

import com.product.manager.product.manager.core.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryPersistencePort {
    Category saveCategory(Category category);
    Optional<Category> findCategoryById(Long id);
    List<Category> findAllCategories();
    void deleteCategoryById(Long id);
    boolean existsByName(String name);
}

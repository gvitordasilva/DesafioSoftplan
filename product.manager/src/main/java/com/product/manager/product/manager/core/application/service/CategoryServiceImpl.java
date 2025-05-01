package com.product.manager.product.manager.core.application.service;

import com.product.manager.product.manager.core.application.ports.CategoryPersistencePort;
import com.product.manager.product.manager.core.application.ports.CategoryServicePort;
import com.product.manager.product.manager.core.domain.exception.CategoryNotFoundException;
import com.product.manager.product.manager.core.domain.model.Category;
import com.product.manager.product.manager.core.dto.CategoryRequestDTO;
import com.product.manager.product.manager.core.dto.CategoryResponseDTO;
import com.product.manager.product.manager.infrastructure.persistence.mapper.CategoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryServicePort {

    private final CategoryPersistencePort persistencePort;
    private final CategoryEntityMapper mapper;

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        Category category = mapper.toDomain(dto);
        Category savedCategory = persistencePort.saveCategory(category);
        return mapper.toDTO(savedCategory);
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        return persistencePort.findCategoryById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return persistencePort.findAllCategories().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO dto) {
        Category existingCategory = persistencePort.findCategoryById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        existingCategory.setName(dto.getName());
        existingCategory.setDescription(dto.getDescription());

        Category updatedCategory = persistencePort.saveCategory(existingCategory);
        return mapper.toDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        if (persistencePort.findCategoryById(id).isEmpty()) {
            throw new CategoryNotFoundException(id);
        }
        persistencePort.deleteCategoryById(id);
    }
}

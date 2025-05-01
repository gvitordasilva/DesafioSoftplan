package com.product.manager.product.manager.core.application.ports;

import com.product.manager.product.manager.core.dto.CategoryRequestDTO;
import com.product.manager.product.manager.core.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryServicePort {
    CategoryResponseDTO createCategory(CategoryRequestDTO dto);
    CategoryResponseDTO getCategoryById(Long id);
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO dto);
    void deleteCategory(Long id);
}

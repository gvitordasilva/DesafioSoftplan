package com.product.manager.product.manager.infrastructure.rest;

import com.product.manager.product.manager.core.application.ports.CategoryServicePort;
import com.product.manager.product.manager.core.dto.CategoryRequestDTO;
import com.product.manager.product.manager.core.dto.CategoryResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServicePort categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDTO createCategory(@Valid @RequestBody CategoryRequestDTO dto) {
        return categoryService.createCategory(dto);
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO getCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequestDTO dto
    ) {
        return categoryService.updateCategory(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}

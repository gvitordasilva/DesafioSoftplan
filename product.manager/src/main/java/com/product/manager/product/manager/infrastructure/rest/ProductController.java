package com.product.manager.product.manager.infrastructure.rest;

import com.product.manager.product.manager.core.application.ports.ProductServicePort;
import com.product.manager.product.manager.core.dto.ProductRequestDTO;
import com.product.manager.product.manager.core.dto.ProductResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServicePort productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO createProduct(@Valid @RequestBody ProductRequestDTO dto) {
        return productService.createProduct(dto);
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO dto
    ) {
        return productService.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
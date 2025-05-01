package com.product.manager.product.manager;

import com.product.manager.product.manager.core.application.ports.CategoryPersistencePort;
import com.product.manager.product.manager.core.application.ports.ProductPersistencePort;
import com.product.manager.product.manager.core.application.service.ProductServiceImpl;
import com.product.manager.product.manager.core.domain.exception.ProductNotFoundException;
import com.product.manager.product.manager.core.domain.model.Category;
import com.product.manager.product.manager.core.domain.model.Product;
import com.product.manager.product.manager.core.dto.ProductRequestDTO;
import com.product.manager.product.manager.core.dto.ProductResponseDTO;
import com.product.manager.product.manager.infrastructure.persistence.mapper.ProductEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductPersistencePort persistencePort;

    @Mock
    private ProductEntityMapper mapper;

    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void createProduct_ShouldReturnProductResponse() {
        // Arrange
        Long categoryId = 2L;
        ProductRequestDTO request = new ProductRequestDTO(
                "Notebook", "16GB RAM", BigDecimal.valueOf(4500), categoryId
        );

        Category mockCategory = Category.builder().id(categoryId).name("Electronics").build();
        Product product = Product.builder()
                .name("Notebook")
                .description("16GB RAM")
                .price(BigDecimal.valueOf(4500))
                .category(mockCategory)
                .build();

        Product savedProduct = Product.builder()
                .id(1L)
                .createdAt(LocalDateTime.now())
                .category(mockCategory)
                .build();

        ProductResponseDTO expectedResponse = new ProductResponseDTO(
                1L, "Notebook", "16GB RAM", BigDecimal.valueOf(4500),
                null, null, null
        );

        when(categoryPersistencePort.findCategoryById(categoryId))
                .thenReturn(Optional.of(mockCategory));

        when(mapper.toDomain(request)).thenReturn(product);
        when(persistencePort.saveProduct(product)).thenReturn(savedProduct);
        when(mapper.toDTO(savedProduct)).thenReturn(expectedResponse);

        ProductResponseDTO response = productService.createProduct(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        verify(persistencePort, times(1)).saveProduct(product);
        verify(categoryPersistencePort, times(1)).findCategoryById(categoryId);
    }

    @Test
    void getProductById_WhenProductExists_ShouldReturnProduct() {
        Long productId = 1L;
        Product product = Product.builder().id(productId).name("Notebook").build();
        ProductResponseDTO expectedResponse = new ProductResponseDTO(productId, "Notebook", null, null, null, null, null);

        when(persistencePort.findProductById(productId)).thenReturn(Optional.of(product));
        when(mapper.toDTO(product)).thenReturn(expectedResponse);

        ProductResponseDTO response = productService.getProductById(productId);

        assertNotNull(response);
        assertEquals(productId, response.getId());
    }

    @Test
    void getProductById_WhenProductNotExists_ShouldThrowException() {
        Long productId = 999L;
        when(persistencePort.findProductById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(productId));
    }

    @Test
    void updateProduct_WhenProductNotExists_ShouldThrowProductNotFoundException() {
        Long productId = 999L;
        ProductRequestDTO dto = new ProductRequestDTO("Name", "Desc", BigDecimal.ONE, 1L);
        when(persistencePort.findProductById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(productId, dto));
        verify(persistencePort, never()).saveProduct(any());
    }

    @Test
    void deleteProduct_WhenProductExists_ShouldDeleteSuccessfully() {
        Long productId = 1L;
        Product existingProduct = Product.builder().id(productId).build();
        when(persistencePort.findProductById(productId)).thenReturn(Optional.of(existingProduct));
        doNothing().when(persistencePort).deleteProductById(productId);

        productService.deleteProduct(productId);

        verify(persistencePort, times(1)).findProductById(productId);
        verify(persistencePort, times(1)).deleteProductById(productId);
    }

    @Test
    void deleteProduct_WhenProductNotExists_ShouldThrowProductNotFoundException() {
        Long productId = 999L;
        when(persistencePort.findProductById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(productId));
        verify(persistencePort, never()).deleteProductById(productId);
    }

}

package com.product.manager.product.manager.core.domain.model;

import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    @Setter
    private String name;
    @Setter
    private String description;
    @Setter
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @Setter
    private Category category;
}
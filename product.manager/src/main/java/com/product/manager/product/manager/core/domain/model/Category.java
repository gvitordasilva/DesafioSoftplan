package com.product.manager.product.manager.core.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Setter
    private Long id;
    @Setter
    private String name;
    @Setter
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
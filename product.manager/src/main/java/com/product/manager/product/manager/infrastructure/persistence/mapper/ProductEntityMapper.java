package com.product.manager.product.manager.infrastructure.persistence.mapper;

import com.product.manager.product.manager.core.domain.model.Category;
import com.product.manager.product.manager.core.domain.model.Product;
import com.product.manager.product.manager.core.dto.ProductRequestDTO;
import com.product.manager.product.manager.core.dto.ProductResponseDTO;
import com.product.manager.product.manager.infrastructure.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {CategoryEntityMapper.class})
public interface ProductEntityMapper {

    @Mapping(target = "category", source = "category")
    ProductEntity toEntity(Product product);

    @Mapping(target = "category", source = "category")
    Product toDomain(ProductEntity entity);

    @Mapping(target = "category", source = "categoryId", qualifiedByName = "mapCategoryIdToCategory")
    Product toDomain(ProductRequestDTO dto);

    @Named("mapCategoryIdToCategory")
    default Category mapCategoryIdToCategory(Long categoryId) {
        if (categoryId == null) return null;
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }

    @Mapping(target = "category", source = "category")
    ProductResponseDTO toDTO(Product product);
}
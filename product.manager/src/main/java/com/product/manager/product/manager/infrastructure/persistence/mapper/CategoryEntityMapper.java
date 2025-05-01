package com.product.manager.product.manager.infrastructure.persistence.mapper;

import com.product.manager.product.manager.core.domain.model.Category;
import com.product.manager.product.manager.core.dto.CategoryRequestDTO;
import com.product.manager.product.manager.core.dto.CategoryResponseDTO;
import com.product.manager.product.manager.infrastructure.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CategoryEntity toEntity(Category category);

    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    Category toDomain(CategoryEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Category toDomain(CategoryRequestDTO dto);

    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    CategoryResponseDTO toDTO(Category category);
}

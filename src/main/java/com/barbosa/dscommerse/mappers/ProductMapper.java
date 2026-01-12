package com.barbosa.dscommerse.mappers;

import com.barbosa.dscommerse.dtos.ProductDTO;
import com.barbosa.dscommerse.dtos.ProductMinDTO;
import com.barbosa.dscommerse.entities.Category;
import com.barbosa.dscommerse.entities.Product;
import com.barbosa.dscommerse.repositories.CategoryRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {

    @Mapping(source = "category", target = "categories")
    ProductDTO toDto(Product product);

    Product toEntity(ProductDTO productDTO, @Context CategoryRepository categoryRepository);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(
            ProductDTO dto,
            @MappingTarget Product entity,
            @Context CategoryRepository categoryRepository
    );

    ProductMinDTO toDtoMin(Product product);

    @AfterMapping
    default void mapCategories(
            ProductDTO dto,
            @MappingTarget Product entity,
            @Context CategoryRepository categoryRepository) {
        entity.getCategory().clear();

        dto.getCategories().forEach(catDto -> {
            Category category = categoryRepository.getReferenceById(catDto.getId());
            entity.getCategory().add(category);
        });
    }
}
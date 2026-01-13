package com.barbosa.dscommerse.mappers;

import com.barbosa.dscommerse.dtos.CategoryDTO;
import com.barbosa.dscommerse.dtos.ProductDTO;
import com.barbosa.dscommerse.dtos.ProductMinDTO;
import com.barbosa.dscommerse.entities.Category;
import com.barbosa.dscommerse.entities.Product;
import com.barbosa.dscommerse.repositories.CategoryRepository;
import org.mapstruct.*;

import java.util.List;

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

        List<Long> ids = dto.getCategories()
                .stream()
                .map(CategoryDTO::getId)
                .toList();

        List<Category> categories = categoryRepository.findAllById(ids);

        if (categories.size() != ids.size()) {
            throw new IllegalArgumentException("Uma ou mais categorias não existem");
        }

        entity.getCategory().addAll(categories);
    }
}
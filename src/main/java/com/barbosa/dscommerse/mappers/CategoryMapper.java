package com.barbosa.dscommerse.mappers;

import com.barbosa.dscommerse.dtos.CategoryDTO;
import com.barbosa.dscommerse.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDto(Category category);
    Category toEntity(CategoryDTO categoryDTO);

}

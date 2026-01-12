package com.barbosa.dscommerse.mappers;

import com.barbosa.dscommerse.dtos.CategoryDTO;
import com.barbosa.dscommerse.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDto(Category category);
    Category toEntity(CategoryDTO categoryDTO);

}

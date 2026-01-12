package com.barbosa.dscommerse.mappers;

import com.barbosa.dscommerse.dtos.ProductDTO;
import com.barbosa.dscommerse.dtos.ProductMinDTO;
import com.barbosa.dscommerse.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO productDTO);
    ProductMinDTO toDtoMin(Product product);
}
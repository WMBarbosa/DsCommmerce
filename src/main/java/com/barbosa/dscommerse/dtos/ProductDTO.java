package com.barbosa.dscommerse.dtos;

import com.barbosa.dscommerse.entities.Product;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductDTO {

    private Long id;

    @Size(min = 3, max = 80, message = "Name must be between 3 and 80 characters")
    @NotBlank(message = "Name is required")
    private String name;

    @Size(min = 10, message = "Description must be at least 10 characters")
    @NotBlank(message = "Description is required")
    private String description;

    @Positive(message = "Price must be positive")
    private Double price;

    private String imgUrl;

    @NotEmpty(message = "Categories are required")
    private List<CategoryDTO> categories = new ArrayList<>();

}

package com.barbosa.dscommerse.dtos;

import com.barbosa.dscommerse.entities.Product;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public Double getPrice() {
        return price;
    }


    public String getImgUrl() {
        return imgUrl;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductDTO that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}

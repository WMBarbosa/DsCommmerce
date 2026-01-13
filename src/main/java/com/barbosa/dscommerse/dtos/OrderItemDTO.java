package com.barbosa.dscommerse.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;
    private String imageUrl;


    public Double getSubTotal() {
        if (price == null || quantity == null) {
            return 0.0;
        }
        return price * quantity;
    }
}

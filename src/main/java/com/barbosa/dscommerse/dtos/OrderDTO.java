package com.barbosa.dscommerse.dtos;

import com.barbosa.dscommerse.entities.enums.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;
    private ClientDTO client;
    private PaymentDTO payment;

    @NotEmpty(message = "Order must have at least one item")
    private List<OrderItemDTO> items = new ArrayList<>();

}

package com.barbosa.dscommerse.mappers;

import com.barbosa.dscommerse.dtos.OrderDTO;
import com.barbosa.dscommerse.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {

    OrderDTO toDto(Order order);
    Order toEntity(OrderDTO orderDTO);
}

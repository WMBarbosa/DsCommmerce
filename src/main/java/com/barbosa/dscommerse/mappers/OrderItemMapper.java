package com.barbosa.dscommerse.mappers;

import com.barbosa.dscommerse.dtos.OrderItemDTO;
import com.barbosa.dscommerse.entities.Order;
import com.barbosa.dscommerse.entities.OrderItem;
import com.barbosa.dscommerse.entities.Product;
import com.barbosa.dscommerse.repositories.ProductsRepository;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "productId", expression = "java(orderItem.getProduct() != null ? orderItem.getProduct().getId() : null)")
    @Mapping(target = "name", expression = "java(orderItem.getProduct() != null ? orderItem.getProduct().getName() : null)")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "imageUrl", source = "product.imgUrl")
    OrderItemDTO toDto(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    OrderItem toEntity(
            OrderItemDTO dto,
            @Context Order order,
            @Context ProductsRepository productRepository
    );

    @AfterMapping
    default void afterMapping(
            OrderItemDTO dto,
            @MappingTarget OrderItem entity,
            @Context Order order,
            @Context ProductsRepository productRepository
    ) {
        Product product = productRepository.getReferenceById(dto.getProductId());

        entity.setOrder(order);
        entity.setProduct(product);
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.getProduct().setImgUrl(dto.getImageUrl());
    }

}

package com.barbosa.dscommerse.factories;

import com.barbosa.dscommerse.dtos.*;
import com.barbosa.dscommerse.entities.*;
import com.barbosa.dscommerse.entities.enums.OrderStatus;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factory {

    public static Product createProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Phone");
        product.setDescription("Good Phone");
        product.setPrice(800.0);
        product.setImgUrl("https://img.com/img.png");

        Category cat = new Category();
        cat.setId(1L);
        cat.setName("Electronics");

        product.getCategories().add(cat);
        return product;
    }

    public static Product createProduct(Long id, String name, String description, Double price, String imgUrl) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImgUrl(imgUrl);
        return product;
    }

    public static ProductDTO createProductDTO() {
        ProductDTO dto = new ProductDTO();
        dto.setName("Phone");
        dto.setDescription("Good Phone");
        dto.setPrice(800.0);
        dto.setImgUrl("https://img.com/img.png");

        CategoryDTO catDto = new CategoryDTO();
        catDto.setId(1L);
        catDto.setName("Electronics");

        dto.setCategories(List.of(catDto));
        return dto;
    }

    public static Category createCategory() {
        Category cat = new Category();
        cat.setId(1L);
        cat.setName("Electronics");
        return cat;
    }

    public static Category createCategory(Long id, String name) {
        Category cat = new Category();
        cat.setId(id);
        cat.setName(name);
        return cat;
    }

    public static CategoryDTO createCategoryDTO() {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(1L);
        dto.setName("Electronics");
        return dto;
    }

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Maria Brown");
        user.setEmail("maria@gmail.com");
        user.setPhone("988888888");
        user.setPassword("$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG");
        user.setBirthDate(LocalDate.of(2001, 7, 25));
        user.addRoles(new Role(1L, "ROLE_CLIENT"));
        return user;
    }

    public static User createAdminUser() {
        User user = new User();
        user.setId(2L);
        user.setName("Alex Green");
        user.setEmail("alex@gmail.com");
        user.setPhone("977777777");
        user.setPassword("$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG");
        user.setBirthDate(LocalDate.of(1987, 12, 13));
        user.addRoles(new Role(1L, "ROLE_CLIENT"));
        user.addRoles(new Role(2L, "ROLE_ADMIN"));
        return user;
    }

    public static UserDTO createUserDTO() {
        UserDTO dto = new UserDTO();
        dto.setId(1L);
        dto.setName("Maria Brown");
        dto.setEmail("maria@gmail.com");
        dto.setPhone("988888888");
        dto.setBirthDate(LocalDate.of(2001, 7, 25));
        dto.setRoles(List.of("ROLE_CLIENT"));
        return dto;
    }

    public static Order createOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setMoment(Instant.parse("2022-07-25T13:00:00Z"));
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(createUser());
        return order;
    }

    public static OrderDTO createOrderDTO() {
        OrderDTO dto = new OrderDTO();
        dto.setId(1L);
        dto.setMoment(Instant.parse("2022-07-25T13:00:00Z"));
        dto.setStatus(OrderStatus.WAITING_PAYMENT);
        dto.setClient(new ClientDTO(1L, "Maria Brown"));
        List<OrderItemDTO> items = new ArrayList<>();
        OrderItemDTO item = new OrderItemDTO();
        item.setProductId(1L);
        item.setQuantity(2);
        items.add(item);
        dto.setItems(items);
        return dto;
    }

    public static OrderItemDTO createOrderItemDTO(Long productId, Integer quantity) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setProductId(productId);
        dto.setQuantity(quantity);
        return dto;
    }
}

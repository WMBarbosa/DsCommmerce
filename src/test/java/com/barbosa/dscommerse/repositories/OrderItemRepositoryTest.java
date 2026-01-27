package com.barbosa.dscommerse.repositories;

import com.barbosa.dscommerse.entities.Order;
import com.barbosa.dscommerse.entities.OrderItem;
import com.barbosa.dscommerse.entities.OrderItemPK;
import com.barbosa.dscommerse.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Test
    void findByIdShouldReturnOrderItemWhenCompositeKeyExists() {
        Order order = orderRepository.findByIdWithItemsAndProducts(1L).orElseThrow();
        Product product = productsRepository.findById(1L).orElseThrow();
        OrderItemPK pk = new OrderItemPK(order, product);

        var result = orderItemRepository.findById(pk);

        assertTrue(result.isPresent());
        assertEquals(2, result.get().getQuantity());
    }
}

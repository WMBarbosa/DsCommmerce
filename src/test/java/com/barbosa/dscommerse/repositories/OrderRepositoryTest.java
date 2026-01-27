package com.barbosa.dscommerse.repositories;

import com.barbosa.dscommerse.entities.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 9999L;
    }

    @Test
    void findByIdWithItemsAndProductsShouldReturnOrderWhenIdExists() {
        Optional<Order> result = orderRepository.findByIdWithItemsAndProducts(existingId);
        assertTrue(result.isPresent());
        assertEquals(existingId, result.get().getId());
        assertNotNull(result.get().getClient());
        assertFalse(result.get().getItems().isEmpty());
    }

    @Test
    void findByIdWithItemsAndProductsShouldReturnEmptyWhenIdDoesNotExist() {
        Optional<Order> result = orderRepository.findByIdWithItemsAndProducts(nonExistingId);
        assertTrue(result.isEmpty());
    }
}

package com.barbosa.dscommerse.service;

import com.barbosa.dscommerse.dtos.OrderDTO;
import com.barbosa.dscommerse.entities.Order;
import com.barbosa.dscommerse.entities.Product;
import com.barbosa.dscommerse.mappers.OrderMapper;
import com.barbosa.dscommerse.repositories.OrderItemRepository;
import com.barbosa.dscommerse.repositories.OrderRepository;
import com.barbosa.dscommerse.repositories.ProductsRepository;
import com.barbosa.dscommerse.repositories.UserRepository;
import com.barbosa.dscommerse.service.serviceException.ResourceNotFoundException;
import com.barbosa.dscommerse.factories.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Mock
    private ProductsRepository productsRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private OrderService orderService;

    private Long existingId;
    private Long nonExistingId;
    private Order order;
    private OrderDTO orderDTO;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 9999L;
        order = Factory.createOrder();
        orderDTO = Factory.createOrderDTO();
    }

    @Test
    void findByIdShouldReturnOrderDTOWhenIdExists() {
        when(orderRepository.findByIdWithItemsAndProducts(existingId)).thenReturn(Optional.of(order));
        doNothing().when(authService).validateSelfOrAdmin(order.getClient().getId());
        when(orderMapper.toDto(order)).thenReturn(orderDTO);

        OrderDTO result = orderService.findById(existingId);

        assertNotNull(result);
        assertEquals(orderDTO.getId(), result.getId());
        verify(orderRepository).findByIdWithItemsAndProducts(existingId);
        verify(authService).validateSelfOrAdmin(order.getClient().getId());
        verify(orderMapper).toDto(order);
    }

    @Test
    void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        when(orderRepository.findByIdWithItemsAndProducts(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.findById(nonExistingId));
        verify(orderRepository).findByIdWithItemsAndProducts(nonExistingId);
        verify(authService, never()).validateSelfOrAdmin(anyLong());
        verify(orderMapper, never()).toDto(any());
    }

    @Test
    void insertShouldReturnOrderDTO() {
        when(userService.authenticate()).thenReturn(Factory.createUser());
        when(productsRepository.getReferenceById(1L)).thenReturn(Factory.createProduct());
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(orderMapper.toDto(any(Order.class))).thenReturn(orderDTO);

        OrderDTO result = orderService.insert(orderDTO);

        assertNotNull(result);
        verify(userService).authenticate();
        verify(productsRepository).getReferenceById(1L);
        verify(orderRepository).save(any(Order.class));
        verify(orderMapper).toDto(any(Order.class));
    }
}

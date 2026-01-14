package com.barbosa.dscommerse.service;

import com.barbosa.dscommerse.dtos.OrderDTO;
import com.barbosa.dscommerse.dtos.OrderItemDTO;
import com.barbosa.dscommerse.entities.Order;
import com.barbosa.dscommerse.entities.OrderItem;
import com.barbosa.dscommerse.entities.Product;
import com.barbosa.dscommerse.entities.User;
import com.barbosa.dscommerse.entities.enums.OrderStatus;
import com.barbosa.dscommerse.mappers.OrderMapper;
import com.barbosa.dscommerse.repositories.OrderItemRepository;
import com.barbosa.dscommerse.repositories.OrderRepository;
import com.barbosa.dscommerse.repositories.ProductsRepository;
import com.barbosa.dscommerse.repositories.UserRepository;
import com.barbosa.dscommerse.service.serviceException.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ProductsRepository productsRepository;
    private final OrderItemRepository orderItemRepository;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
         Order order = orderRepository.findByIdWithItemsAndProducts(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
         authService.validateSelfOrAdmin(order.getClient().getId());
        return orderMapper.toDto(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO orderDTO) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(userService.authenticate());

        orderDTO.getItems().forEach(item -> {
            Product product = productsRepository.getReferenceById(item.getProductId());
            order.addItem(product, item.getQuantity());
        });

        orderRepository.save(order);
        return orderMapper.toDto(order);
    }
}

package com.barbosa.dscommerse.controllers;

import com.barbosa.dscommerse.dtos.OrderDTO;
import com.barbosa.dscommerse.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById (@PathVariable Long id) {
        OrderDTO OrderDTO = orderService.findById(id);
        return ResponseEntity.ok().body(OrderDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO OrderDTO) {
        OrderDTO createdOrder = orderService.insert(OrderDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdOrder.getId()).toUri();
        return ResponseEntity.created(uri).body(createdOrder);

    }

}

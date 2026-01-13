package com.barbosa.dscommerse.controllers;

import com.barbosa.dscommerse.dtos.OrderDTO;
import com.barbosa.dscommerse.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URL;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById (@PathVariable Long id) {
        OrderDTO OrderDTO = orderService.findById(id);
        return ResponseEntity.ok().body(OrderDTO);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO OrderDTO) {
        OrderDTO createdOrder = orderService.insert(OrderDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdOrder.getId()).toUri();
        return ResponseEntity.created(uri).body(createdOrder);

    }

}

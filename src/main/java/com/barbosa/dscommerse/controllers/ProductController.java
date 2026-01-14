package com.barbosa.dscommerse.controllers;

import com.barbosa.dscommerse.dtos.ProductDTO;
import com.barbosa.dscommerse.dtos.ProductMinDTO;
import com.barbosa.dscommerse.entities.Product;
import com.barbosa.dscommerse.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductMinDTO>> findAll(
            @RequestParam (name = "name", defaultValue = "") String name, Pageable pageable) {
        Page<ProductMinDTO> dtoList = productService.findAll(name, pageable);
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById (@PathVariable Long id) {
        ProductDTO productDTO = productService.findById(id);
        return ResponseEntity.ok().body(productDTO);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) {
        ProductDTO productDTO = productService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(productDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(productDTO);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ProductDTO> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProductDTO = productService.update(id, productDTO);
        return ResponseEntity.ok().body(updatedProductDTO);
    }
}

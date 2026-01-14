package com.barbosa.dscommerse.controllers;

import com.barbosa.dscommerse.dtos.CategoryDTO;
import com.barbosa.dscommerse.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> categoryDTO = categoryService.findAll();
        return ResponseEntity.ok().body(categoryDTO);
    }
}

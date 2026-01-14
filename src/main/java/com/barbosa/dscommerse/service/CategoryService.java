package com.barbosa.dscommerse.service;

import com.barbosa.dscommerse.dtos.CategoryDTO;
import com.barbosa.dscommerse.entities.Category;
import com.barbosa.dscommerse.mappers.CategoryMapper;
import com.barbosa.dscommerse.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;


    @Transactional
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(mapper::toDto)
                .toList();
    }

}

package com.barbosa.dscommerse.service;

import com.barbosa.dscommerse.dtos.CategoryDTO;
import com.barbosa.dscommerse.entities.Category;
import com.barbosa.dscommerse.mappers.CategoryMapper;
import com.barbosa.dscommerse.repositories.CategoryRepository;
import com.barbosa.dscommerse.factories.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper mapper;

    @InjectMocks
    private CategoryService categoryService;

    private List<Category> categories;

    @BeforeEach
    void setUp() {
        categories = List.of(Factory.createCategory(), Factory.createCategory(2L, "Livros"));
    }

    @Test
    void findAllShouldReturnListOfCategoryDTO() {
        when(categoryRepository.findAll()).thenReturn(categories);
        when(mapper.toDto(any(Category.class)))
                .thenReturn(Factory.createCategoryDTO())
                .thenReturn(Factory.createCategoryDTO());

        List<CategoryDTO> result = categoryService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(categoryRepository, times(1)).findAll();
        verify(mapper, times(2)).toDto(any(Category.class));
    }
}

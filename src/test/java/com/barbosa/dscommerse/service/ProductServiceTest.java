package com.barbosa.dscommerse.service;

import com.barbosa.dscommerse.dtos.ProductDTO;
import com.barbosa.dscommerse.dtos.ProductMinDTO;
import com.barbosa.dscommerse.entities.Product;
import com.barbosa.dscommerse.mappers.ProductMapper;
import com.barbosa.dscommerse.repositories.CategoryRepository;
import com.barbosa.dscommerse.repositories.ProductsRepository;
import com.barbosa.dscommerse.service.serviceException.ResourceNotFoundException;
import com.barbosa.dscommerse.factories.Factory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductsRepository productsRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductMapper productsMapper;

    @InjectMocks
    private ProductService productService;

    private Long existingId;
    private Long nonExistingId;
    private Product product;
    private ProductDTO productDTO;
    private Page<Product> productPage;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 9999L;
        product = Factory.createProduct();
        productDTO = Factory.createProductDTO();
        productPage = new PageImpl<>(List.of(product));
    }

    @Test
    void findAllShouldReturnPageOfProductMinDTO() {
        when(productsRepository.searchByName(anyString(), any())).thenReturn(productPage);
        when(productsMapper.toDtoMin(any(Product.class))).thenReturn(new ProductMinDTO());

        Page<ProductMinDTO> result = productService.findAll("", PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(productsRepository).searchByName(anyString(), any());
        verify(productsMapper).toDtoMin(any(Product.class));
    }

    @Test
    void findByIdShouldReturnProductDTOWhenIdExists() {
        when(productsRepository.findById(existingId)).thenReturn(Optional.of(product));
        when(productsMapper.toDto(product)).thenReturn(productDTO);

        ProductDTO result = productService.findById(existingId);

        assertNotNull(result);
        assertEquals(productDTO.getName(), result.getName());
        verify(productsRepository).findById(existingId);
        verify(productsMapper).toDto(product);
    }

    @Test
    void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        when(productsRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.findById(nonExistingId));
        verify(productsRepository).findById(nonExistingId);
        verify(productsMapper, never()).toDto(any());
    }

    @Test
    void createShouldReturnProductDTO() {
        when(productsMapper.toEntity(any(ProductDTO.class), any())).thenReturn(product);
        when(productsRepository.save(any(Product.class))).thenReturn(product);
        when(productsMapper.toDto(product)).thenReturn(productDTO);

        ProductDTO result = productService.create(productDTO);

        assertNotNull(result);
        verify(productsMapper).toEntity(eq(productDTO), any());
        verify(productsRepository).save(any(Product.class));
        verify(productsMapper).toDto(product);
    }

    @Test
    void deleteShouldDoNothingWhenIdExists() {
        when(productsRepository.existsById(existingId)).thenReturn(true);
        doNothing().when(productsRepository).deleteById(existingId);

        assertDoesNotThrow(() -> productService.delete(existingId));
        verify(productsRepository).existsById(existingId);
        verify(productsRepository).deleteById(existingId);
    }

    @Test
    void deleteShouldThrowEntityNotFoundExceptionWhenIdDoesNotExist() {
        when(productsRepository.existsById(nonExistingId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> productService.delete(nonExistingId));
        verify(productsRepository).existsById(nonExistingId);
        verify(productsRepository, never()).deleteById(any());
    }

    @Test
    void updateShouldReturnProductDTOWhenIdExists() {
        when(productsRepository.findById(existingId)).thenReturn(Optional.of(product));
        when(productsRepository.save(any(Product.class))).thenReturn(product);
        when(productsMapper.toDto(any(Product.class))).thenReturn(productDTO);

        ProductDTO result = productService.update(existingId, productDTO);

        assertNotNull(result);
        verify(productsRepository).findById(existingId);
        verify(productsMapper).updateEntityFromDto(eq(productDTO), eq(product), any());
        verify(productsRepository).save(product);
        verify(productsMapper).toDto(product);
    }

    @Test
    void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        when(productsRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.update(nonExistingId, productDTO));
        verify(productsRepository).findById(nonExistingId);
        verify(productsRepository, never()).save(any());
    }
}

package com.barbosa.dscommerse.service;

import com.barbosa.dscommerse.dtos.CategoryDTO;
import com.barbosa.dscommerse.dtos.ProductDTO;
import com.barbosa.dscommerse.dtos.ProductMinDTO;
import com.barbosa.dscommerse.entities.Category;
import com.barbosa.dscommerse.entities.Product;
import com.barbosa.dscommerse.mappers.ProductMapper;
import com.barbosa.dscommerse.repositories.CategoryRepository;
import com.barbosa.dscommerse.repositories.ProductsRepository;
import com.barbosa.dscommerse.service.serviceException.DatabaseException;
import com.barbosa.dscommerse.service.serviceException.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productsMapper;
    private final ProductsRepository productsRepository;
    private final CategoryRepository categoryRepository;


    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
        Page<Product> products = productsRepository.searchByName(name, pageable);
        return products.map(productsMapper::toDtoMin);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return productsMapper.toDto(product);
    }

    @Transactional
    public ProductDTO create(ProductDTO productDTO) {
        Product product = productsMapper.toEntity(productDTO, categoryRepository);
        product = productsRepository.save(product);
        return productsMapper.toDto(product);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productsRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found" + id);
        }
        try {
            productsRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não é possível excluir o produto porque está associado a um pedido" + id);
        }
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product product = productsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        productsMapper.updateEntityFromDto(productDTO, product, categoryRepository);
        return productsMapper.toDto(productsRepository.save(product));
    }
}

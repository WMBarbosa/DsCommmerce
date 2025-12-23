package com.barbosa.dscommerse.service;

import com.barbosa.dscommerse.dtos.ProductDTO;
import com.barbosa.dscommerse.entities.Product;
import com.barbosa.dscommerse.repositories.ProductsRepository;
import com.barbosa.dscommerse.service.serviceException.DatabaseException;
import com.barbosa.dscommerse.service.serviceException.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(String name, Pageable pageable) {
        Page<Product> products = productsRepository.searchByName(name, pageable);
        return products.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO create(ProductDTO productDTO) {
        Product product = productsRepository.save(productDTO.toEntity());
        return new ProductDTO(product);
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
        updateData(product, productDTO);
        return new ProductDTO(productsRepository.save(product));
    }


    public void updateData(Product product,ProductDTO productDTO) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImgUrl());
    }

}

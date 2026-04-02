package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.ProductRequestDto;
import com.ecommerce.productservice.dto.ProductResponseDto;
import com.ecommerce.productservice.exception.ProductException;
import com.ecommerce.productservice.exception.ProductNotFoundException;
import com.ecommerce.productservice.mapper.ProductMapper;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        productMapper.dtoToProduct(productRequestDto);
        Product newProduct = productRepository.save(productMapper.dtoToProduct(productRequestDto));
        return productMapper.productToDto(newProduct);
    }

    @Transactional(readOnly = true)
    public ProductResponseDto findProductByName(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return productMapper.productToDto(product);
    }

    @Transactional(readOnly = true)
    public ProductResponseDto findProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return productMapper.productToDto(product);
    }

    @Transactional
    public ProductResponseDto updateProduct(UUID id, ProductRequestDto productRequestDto) {
        if (productRepository.findById(id).isEmpty())
            throw new ProductException("Product with id " + id + " not exists");

        Product updatedProduct = productMapper.dtoToProduct(productRequestDto);
        updatedProduct.setId(id);
        productRepository.save(updatedProduct);

        return productMapper.productToDto(updatedProduct);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> findAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);

        return products.map(productMapper::productToDto);
    }


}

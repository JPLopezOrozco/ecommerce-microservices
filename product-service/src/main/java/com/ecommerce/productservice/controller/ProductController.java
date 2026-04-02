package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.dto.ProductRequestDto;
import com.ecommerce.productservice.dto.ProductResponseDto;
import com.ecommerce.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;



    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductRequestDto productRequestDto) {
        ProductResponseDto productResponseDto = productService.createProduct(productRequestDto);
        var location = UriComponentsBuilder
                .fromPath("/products/{id}")
                .buildAndExpand(productResponseDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(productResponseDto);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable UUID id) {
        ProductResponseDto productResponseDto = productService.findProductById(id);
        return ResponseEntity.ok(productResponseDto);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductResponseDto> getProductByName(@PathVariable String name) {
        ProductResponseDto productResponseDto = productService.findProductByName(name);
        return ResponseEntity.ok(productResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getProducts(Pageable pageable) {
        Page<ProductResponseDto> productResponseDtos = productService.findAllProducts(pageable);
        return ResponseEntity.ok(productResponseDtos);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody @Valid ProductRequestDto productRequestDto, @PathVariable UUID id) {
        ProductResponseDto productResponseDto = productService.updateProduct(id, productRequestDto);
        return ResponseEntity.ok(productResponseDto);
    }


}

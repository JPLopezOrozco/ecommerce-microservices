package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.dto.ProductRequestDto;
import com.ecommerce.productservice.dto.ProductResponseDto;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.model.ProductCategory;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product dtoToProduct(ProductRequestDto productRequestDto) {
        Integer productStock = productRequestDto.getStock() != null ? productRequestDto.getStock() : 0;
        ProductCategory productCategory = productRequestDto.getCategory() != null ?
                ProductCategory.valueOf(productRequestDto.getCategory()) : null;

        return Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .stock(productStock)
                .category(productCategory)
                .imageUrl(productRequestDto.getImageUrl())
                .build();
    }
    public ProductResponseDto productToDto(Product product) {
        String productCategory = product.getCategory().toString();
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .imageUrl(product.getImageUrl())
                .category(productCategory)
                .build();
    }



}

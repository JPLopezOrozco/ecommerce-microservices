package com.ecommerce.productservice.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    @NotBlank(message = "Product must have name")
    private String name;
    private String description;
    @NotNull(message = "Product must have price")
    @Min(value = 1, message = "Price must be greater than 0")
    private BigDecimal price;
    @Positive(message = "Stock cannot be negative")
    private Integer stock;
    private String imageUrl;
    private String category;

}

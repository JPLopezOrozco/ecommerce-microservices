package com.ecommerce.productservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 500)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer stock;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    private String imageUrl;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Product product)) return false;
        return id != null && id.equals((product.id));
    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}



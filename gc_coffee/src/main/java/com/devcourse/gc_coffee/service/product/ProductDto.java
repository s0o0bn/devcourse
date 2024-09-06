package com.devcourse.gc_coffee.service.product;

import com.devcourse.gc_coffee.domain.product.Product;
import jakarta.validation.constraints.NotBlank;

public record ProductDto(@NotBlank String name,
                         @NotBlank String category,
                         int price,
                         String description) {
    public Product toEntity() {
        return Product.builder()
                .name(name)
                .category(category)
                .price(price)
                .description(description)
                .build();
    }
}

package com.devcourse.gc_coffee.product.dto.request;

import com.devcourse.gc_coffee.product.domain.Product;
import jakarta.validation.constraints.NotBlank;

public record ProductRequest(@NotBlank String name,
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

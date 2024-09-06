package com.devcourse.gc_coffee.product.dto;

import com.devcourse.gc_coffee.product.domain.Product;
import lombok.Builder;

@Builder
public record ProductDto(String id, String name, String category, long price, String description) {
    public static ProductDto from(Product entity) {
        return ProductDto.builder()
                .id(entity.getId().toString())
                .name(entity.getName())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .build();
    }
}

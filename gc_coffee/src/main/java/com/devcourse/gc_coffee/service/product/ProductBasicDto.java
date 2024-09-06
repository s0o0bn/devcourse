package com.devcourse.gc_coffee.service.product;

import com.devcourse.gc_coffee.domain.product.Product;
import lombok.Builder;

@Builder
public record ProductBasicDto(String id, String name, String category, long price, String description) {
    public static ProductBasicDto from(Product entity) {
        return ProductBasicDto.builder()
                .id(entity.getId().toString())
                .name(entity.getName())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .build();
    }
}

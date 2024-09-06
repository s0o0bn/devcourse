package com.devcourse.gc_coffee.service.product;

import com.devcourse.gc_coffee.common.util.TimestampUtil;
import com.devcourse.gc_coffee.domain.product.Product;
import lombok.Builder;

@Builder
public record ProductDetailDto(String id,
                               String name,
                               String category,
                               long price,
                               String description,
                               String createdAt,
                               String updatedAt) {
    public static ProductDetailDto from(Product entity) {
        return ProductDetailDto.builder()
                .id(entity.getId().toString())
                .name(entity.getName())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .createdAt(TimestampUtil.format(entity.getCreatedAt()))
                .updatedAt(TimestampUtil.format(entity.getUpdatedAt()))
                .build();
    }
}

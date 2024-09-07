package com.devcourse.gc_coffee.order.dto;

import com.devcourse.gc_coffee.global.util.StringUtil;
import com.devcourse.gc_coffee.order.domain.OrderItem;

public record OrderDetailDto(String category,
                             int quantity,
                             String price) {
    public static OrderDetailDto from(OrderItem entity) {
        return new OrderDetailDto(
                entity.getCategory(),
                entity.getQuantity(),
                StringUtil.formatNumber2Price(entity.getPrice())
        );
    }
}

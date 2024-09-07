package com.devcourse.gc_coffee.order.dto;

import com.devcourse.gc_coffee.global.util.StringUtil;
import com.devcourse.gc_coffee.global.util.TimestampUtil;
import com.devcourse.gc_coffee.order.domain.Order;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderDto(String id,
                       String email,
                       String address,
                       String postcode,
                       String totalPrice,
                       String orderStatus,
                       String createdAt,
                       String updatedAt,
                       List<OrderDetailDto> details) {
    public static OrderDto from(Order entity) {
        List<OrderDetailDto> details = entity.getOrderItems().stream()
                .map(OrderDetailDto::from)
                .toList();
        return OrderDto.builder()
                .id(entity.getId().toString())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .postcode(entity.getPostcode())
                .totalPrice(StringUtil.formatNumber2Price(entity.getTotalPrice()))
                .orderStatus(entity.getOrderStatus().getStatus())
                .createdAt(TimestampUtil.format(entity.getCreatedAt()))
                .updatedAt(TimestampUtil.format(entity.getUpdatedAt()))
                .details(details)
                .build();
    }
}

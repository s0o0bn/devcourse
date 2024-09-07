package com.devcourse.gc_coffee.order.dto;

import com.devcourse.gc_coffee.global.util.StringUtil;
import com.devcourse.gc_coffee.global.util.TimestampUtil;
import com.devcourse.gc_coffee.order.domain.Order;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderBasicDto(String id,
                            String address,
                            String postcode,
                            String totalPrice,
                            String orderStatus,
                            String createdAt,
                            List<OrderDetailDto> details) {
    public static OrderBasicDto from(Order entity) {
        List<OrderDetailDto> details = entity.getOrderItems().stream()
                .map(OrderDetailDto::from)
                .toList();
        return OrderBasicDto.builder()
                .id(entity.getId().toString())
                .address(entity.getAddress())
                .postcode(entity.getPostcode())
                .totalPrice(StringUtil.formatNumber2Price(entity.getTotalPrice()))
                .orderStatus(entity.getOrderStatus().getStatus())
                .createdAt(TimestampUtil.format(entity.getCreatedAt()))
                .details(details)
                .build();
    }
}

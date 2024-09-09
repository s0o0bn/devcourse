package com.devcourse.gc_coffee.order.dto.request;

import com.devcourse.gc_coffee.order.domain.Order;
import com.devcourse.gc_coffee.order.domain.OrderStatus;
import com.devcourse.gc_coffee.order.dto.OrderProductQuantityDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public record OrderRequest(@Email String email,
                           @NotBlank String address,
                           @NotBlank String postcode,
                           @NotEmpty List<OrderProductQuantityDto> items) {
    public Order toEntity() {
        return Order.builder()
                .email(email)
                .address(address)
                .postcode(postcode)
                .orderStatus(OrderStatus.PAYMENT_COMPLETED)
                .build();
    }

    @JsonIgnore
    public List<UUID> getProductsIds() {
        return items.stream()
                .map(item -> UUID.fromString(item.productId()))
                .toList();
    }
}

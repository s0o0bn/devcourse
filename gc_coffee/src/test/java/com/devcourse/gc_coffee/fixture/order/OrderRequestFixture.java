package com.devcourse.gc_coffee.fixture.order;

import com.devcourse.gc_coffee.order.dto.OrderProductQuantityDto;
import com.devcourse.gc_coffee.order.dto.request.OrderRequest;

import java.util.List;
import java.util.UUID;

public class OrderRequestFixture {
    public static OrderRequest getOrderRequest() {
        return new OrderRequest("user@email.com", "address", "postcode", getOrderProductQuantityDtos());
    }

    public static List<OrderProductQuantityDto> getOrderProductQuantityDtos() {
        return List.of(
                new OrderProductQuantityDto(UUID.randomUUID().toString(), 1),
                new OrderProductQuantityDto(UUID.randomUUID().toString(), 2)
        );
    }
}

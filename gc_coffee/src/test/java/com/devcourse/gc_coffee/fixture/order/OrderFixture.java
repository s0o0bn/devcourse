package com.devcourse.gc_coffee.fixture.order;

import com.devcourse.gc_coffee.order.domain.Order;
import com.devcourse.gc_coffee.order.domain.OrderStatus;

import java.lang.reflect.Field;
import java.util.UUID;

public class OrderFixture {
    public static Order getOrder() {
        return Order.builder()
                .email("email")
                .address("address")
                .postcode("postcode")
                .orderStatus(OrderStatus.PAYMENT_COMPLETED)
                .build();
    }

    public static Order getOrderWithId() {
        Order order = getOrder();

        try {
            Field field = Order.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(order, UUID.randomUUID());
        } catch (Exception ignored) {}

        return order;
    }
}

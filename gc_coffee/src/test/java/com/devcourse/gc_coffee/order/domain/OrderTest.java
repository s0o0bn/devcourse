package com.devcourse.gc_coffee.order.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.devcourse.gc_coffee.fixture.order.OrderFixture.getOrder;
import static com.devcourse.gc_coffee.fixture.order.OrderItemFixture.getOrderItems;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {
    @Test
    @DisplayName("주문 총 금액을 계산한다.")
    void calculate_order_total_price() {
        // given
        Order order = getOrder();
        List<OrderItem> orderItems = getOrderItems();
        order.getOrderItems().addAll(orderItems);
        long expected = orderItems.stream()
                .mapToLong(item -> item.getPrice() * item.getQuantity())
                .sum();

        // when
        long actual = order.getTotalPrice();

        // then
        assertEquals(expected, actual);
    }
}

package com.devcourse.gc_coffee.order.service;

import com.devcourse.gc_coffee.order.domain.Order;
import com.devcourse.gc_coffee.order.domain.OrderItem;
import com.devcourse.gc_coffee.order.dto.request.OrderRequest;
import com.devcourse.gc_coffee.order.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.devcourse.gc_coffee.fixture.order.OrderItemFixture.getOrderItems;
import static com.devcourse.gc_coffee.fixture.order.OrderRequestFixture.getOrderRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    @DisplayName("주문을 저장한다.")
    void create_new_order() {
        // given
        OrderRequest request = getOrderRequest();
        List<OrderItem> orderItems = getOrderItems();

        // when
        orderService.saveOrder(request, orderItems);

        // then
        verify(orderRepository).save(any(Order.class));
    }
}

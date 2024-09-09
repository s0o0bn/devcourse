package com.devcourse.gc_coffee.order.service;

import com.devcourse.gc_coffee.global.exception.BadRequestException;
import com.devcourse.gc_coffee.global.exception.UnauthorizedException;
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
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static com.devcourse.gc_coffee.fixture.order.OrderFixture.getOrderWithId;
import static com.devcourse.gc_coffee.fixture.order.OrderItemFixture.getOrderItems;
import static com.devcourse.gc_coffee.fixture.order.OrderRequestFixture.getOrderRequest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    @DisplayName("고객이 주문한 자신의 주문을 취소한다.")
    void delete_order() {
        // given
        Order order = getOrderWithId();

        // when
        when(orderRepository.findById(any(UUID.class))).thenReturn(Optional.of(order));
        orderService.deleteOrder(order.getId().toString(), order.getEmail());

        // then
        verify(orderRepository).findById(any(UUID.class));
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    @DisplayName("주문이 이미 처리 되었을 때, 취소 시 예외가 발생한다.")
    void exception_thrown_when_order_is_already_processed() {
        // given
        Order order = getOrderWithId();
        order.cancel();

        // when
        when(orderRepository.findById(any(UUID.class))).thenReturn(Optional.of(order));

        // then
        assertThrows(BadRequestException.class,
                () -> orderService.deleteOrder(order.getId().toString(), order.getEmail()));
    }

    @Test
    @DisplayName("자신의 주문이 아닌 주문을 취소 시 예외가 발생한다.")
    void exception_thrown_when_order_is_not_mine() {
        // given
        Order order = getOrderWithId();

        // when
        when(orderRepository.findById(any(UUID.class))).thenReturn(Optional.of(order));

        // then
        assertThrows(UnauthorizedException.class,
                () -> orderService.deleteOrder(order.getId().toString(), "other_user@email.com"));
    }

    @Test
    @DisplayName("존재하지 않는 주문을 취소 시 예외가 발생한다.")
    void exception_thrown_when_order_is_not_exists() {
        // given

        // when
        when(orderRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // then
        assertThrows(NoSuchElementException.class,
                () -> orderService.deleteOrder(UUID.randomUUID().toString(), "user@email.com"));
    }
}

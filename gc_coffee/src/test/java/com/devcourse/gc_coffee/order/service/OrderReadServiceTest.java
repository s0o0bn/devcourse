package com.devcourse.gc_coffee.order.service;

import com.devcourse.gc_coffee.order.domain.Order;
import com.devcourse.gc_coffee.order.dto.OrderBasicDto;
import com.devcourse.gc_coffee.order.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.devcourse.gc_coffee.fixture.order.OrderFixture.getOrderWithId;
import static com.devcourse.gc_coffee.fixture.order.OrderItemFixture.getOrderItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderReadServiceTest {
    @InjectMocks
    private OrderReadService orderReadService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    @DisplayName("사용자의 주문 내역을 조회한다.")
    void get_order_list_by_email() {
        // given
        Order order = getOrderWithId();
        order.getOrderItems().addAll(getOrderItems());

        // when
        when(orderRepository.findAllWithItemsByEmail(any(String.class))).thenReturn(List.of(order));
        List<OrderBasicDto> actual = orderReadService.getOrdersOf(order.getEmail());

        // then
        verify(orderRepository).findAllWithItemsByEmail(any(String.class));
        assertEquals(1, actual.size());
        assertEquals(order.getOrderItems().size(), actual.get(0).details().size());
    }
}

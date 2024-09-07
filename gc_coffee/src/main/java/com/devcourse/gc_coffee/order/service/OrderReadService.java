package com.devcourse.gc_coffee.order.service;

import com.devcourse.gc_coffee.admin.order.AdminOrderResponse;
import com.devcourse.gc_coffee.global.util.TimestampUtil;
import com.devcourse.gc_coffee.order.dto.OrderBasicDto;
import com.devcourse.gc_coffee.order.dto.OrderDto;
import com.devcourse.gc_coffee.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderReadService {
    private final OrderRepository orderRepository;

    public AdminOrderResponse getAllOrders() {
        LocalDateTime to = TimestampUtil.getTodayAt(14);
        LocalDateTime from = to.minusDays(1);
        List<OrderDto> todayOrders = orderRepository.findAllWithItemsByCreatedAtBetween(from, to)
                .stream().map(OrderDto::from)
                .toList();
        List<OrderDto> completedOrders = orderRepository.findAllWithItemsOrderByCreatedAtDesc()
                .stream().map(OrderDto::from)
                .toList();

        return new AdminOrderResponse(todayOrders, completedOrders);
    }

    public List<OrderBasicDto> getOrdersOf(String email) {
        return orderRepository.findAllWithItemsByEmail(email)
                .stream().map(OrderBasicDto::from)
                .toList();
    }
}

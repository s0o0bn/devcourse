package com.devcourse.gc_coffee.order.service;

import com.devcourse.gc_coffee.order.domain.Order;
import com.devcourse.gc_coffee.order.domain.OrderItem;
import com.devcourse.gc_coffee.order.dto.request.OrderRequest;
import com.devcourse.gc_coffee.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void saveOrder(OrderRequest request, List<OrderItem> orderItems) {
        Order order = request.toEntity();
        orderItems.forEach(item -> item.setOrder(order));
        order.getOrderItems().addAll(orderItems);
        orderRepository.save(order);
    }
}

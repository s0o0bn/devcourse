package com.devcourse.gc_coffee.order.service;

import com.devcourse.gc_coffee.global.exception.BadRequestException;
import com.devcourse.gc_coffee.global.exception.UnauthorizedException;
import com.devcourse.gc_coffee.order.domain.Order;
import com.devcourse.gc_coffee.order.domain.OrderItem;
import com.devcourse.gc_coffee.order.dto.request.OrderRequest;
import com.devcourse.gc_coffee.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

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

    public void deleteOrder(String id, String email) {
        Order order = orderRepository.findById(UUID.fromString(id))
                .orElseThrow(NoSuchElementException::new);
        if (!order.isOrderedBy(email)) {
            throw new UnauthorizedException();
        }
        if (!order.canBeCanceled()) {
            throw new BadRequestException("This order is already processed.");
        }
        order.cancel();
        orderRepository.save(order);
    }
}

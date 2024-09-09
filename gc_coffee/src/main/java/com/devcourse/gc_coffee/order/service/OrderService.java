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
        Order order = getValidOrder(id, email);
        order.cancel();
        orderRepository.save(order);
    }

    public Order getOrderForUpdate(String id, String email) {
        // 주문 수정 유효 여부 조회
        return getValidOrder(id, email);
    }

    private Order getValidOrder(String id, String email) {
        Order order = orderRepository.findById(UUID.fromString(id))
                .orElseThrow(NoSuchElementException::new);
        if (!order.isOrderedBy(email)) {
            throw new UnauthorizedException(OrderExceptionType.UNAUTHORIZED.getMessage());
        }
        if (!order.isModifiable()) {
            throw new BadRequestException(OrderExceptionType.IS_NOT_MODIFIABLE.getMessage());
        }
        return order;
    }
}

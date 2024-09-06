package com.devcourse.gc_coffee.order.service;

import com.devcourse.gc_coffee.order.domain.OrderItem;
import com.devcourse.gc_coffee.order.dto.OrderProductQuantityDto;
import com.devcourse.gc_coffee.product.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class OrderItemService {
    // 더 최선이 있을 거 같긴 한데...
    public List<OrderItem> createOrderItems(List<OrderProductQuantityDto> productQuantityDtos,
                                           Map<String, Product> productsOfOrder) {
        return productQuantityDtos.stream()
                .map(item -> {
                    if (!productsOfOrder.containsKey(item.productId())) {
                        throw new NoSuchElementException();
                    }
                    return new OrderItem(productsOfOrder.get(item.productId()), item.quantity());
                })
                .toList();
    }
}

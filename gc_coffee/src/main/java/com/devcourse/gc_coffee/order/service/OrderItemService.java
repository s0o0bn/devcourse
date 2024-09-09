package com.devcourse.gc_coffee.order.service;

import com.devcourse.gc_coffee.global.exception.BadRequestException;
import com.devcourse.gc_coffee.order.domain.OrderItem;
import com.devcourse.gc_coffee.order.dto.OrderProductQuantityDto;
import com.devcourse.gc_coffee.order.repository.OrderItemRepository;
import com.devcourse.gc_coffee.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

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

    public void modifyOrderItems(UUID orderId, List<OrderProductQuantityDto> productQuantityDtos) {
        /*
            1. order의 OrderItem 리스트 가져오기 // 주문 수정이 안 될 수도 있어서 Order랑 같이 join 하기보단 그냥 따로 조회하기
            2. 각 OrderItem의 Product.getId()가 일치하는지 확인 (주문 안 한 상품 수정하려 하면 예외 발생)
            3. 각 OrderItem quantity 변경
         */
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(orderId);
        updateProductQuantity(orderItems, productQuantityDtos);
        orderItemRepository.saveAll(orderItems);
    }

    private void updateProductQuantity(List<OrderItem> orderItems,
                                       List<OrderProductQuantityDto> productQuantityDtos) {
        Map<String, OrderItem> itemMap = orderItems.stream()
                .collect(Collectors.toMap(item -> item.getProduct().getId().toString(), item -> item));
        productQuantityDtos.forEach(dto -> {
            OrderItem orderItem = itemMap.get(dto.productId());
            if (orderItem == null) {
                throw new BadRequestException(OrderExceptionType.NOT_ORDERED_PRODUCT.getMessage());
            }
            orderItem.updateQuantity(dto.quantity());
        });
    }
}

package com.devcourse.gc_coffee.order.service.facade;

import com.devcourse.gc_coffee.order.domain.OrderItem;
import com.devcourse.gc_coffee.order.dto.request.OrderRequest;
import com.devcourse.gc_coffee.order.service.OrderItemService;
import com.devcourse.gc_coffee.order.service.OrderService;
import com.devcourse.gc_coffee.product.domain.Product;
import com.devcourse.gc_coffee.product.service.ProductReadService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCase {
    private final OrderService orderService;
    private final ProductReadService productReadService;
    private final OrderItemService orderItemService;

    @Transactional
    public void createOrder(OrderRequest request) {
        List<UUID> productIds = request.getProductsIds();
        Map<String, Product> productsOfOrder = productReadService.mapProductWithId(productIds);
        List<OrderItem> orderItems = orderItemService.createOrderItems(request.items(), productsOfOrder);
        orderService.saveOrder(request, orderItems);
    }
}

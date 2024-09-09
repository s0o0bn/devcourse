package com.devcourse.gc_coffee.order.service.facade;

import com.devcourse.gc_coffee.order.domain.Order;
import com.devcourse.gc_coffee.order.domain.OrderItem;
import com.devcourse.gc_coffee.order.dto.OrderProductQuantityDto;
import com.devcourse.gc_coffee.order.dto.request.OrderRequest;
import com.devcourse.gc_coffee.order.dto.request.UpdateOrderRequest;
import com.devcourse.gc_coffee.order.service.OrderItemService;
import com.devcourse.gc_coffee.order.service.OrderService;
import com.devcourse.gc_coffee.product.domain.Product;
import com.devcourse.gc_coffee.product.service.ProductReadService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static com.devcourse.gc_coffee.fixture.order.OrderFixture.getOrderWithId;
import static com.devcourse.gc_coffee.fixture.order.OrderRequestFixture.getOrderProductQuantityDtos;
import static com.devcourse.gc_coffee.fixture.order.OrderRequestFixture.getOrderRequest;
import static com.devcourse.gc_coffee.fixture.product.ProductFixture.getProductWithId;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderUseCaseTest {
    @InjectMocks
    private OrderUseCase orderUseCase;

    @Mock
    private OrderService orderService;
    @Mock
    private ProductReadService productReadService;
    @Mock
    private OrderItemService orderItemService;

    @Test
    @DisplayName("주문한 상품 정보와 함께 주문 정보를 저장한다.")
    void create_order_with_order_items() {
        // given
        OrderRequest request = getOrderRequest();
        Product product = getProductWithId();
        Map<String, Product> orderProduct = Map.of(product.getId().toString(), product);
        List<OrderItem> orderItems = List.of(new OrderItem(product, 1));

        // when
        when(productReadService.mapProductWithId(anyList())).thenReturn(orderProduct);
        when(orderItemService.createOrderItems(anyList(), anyMap())).thenReturn(orderItems);
        orderUseCase.createOrder(request);

        // then
        verify(productReadService).mapProductWithId(request.getProductsIds());
        verify(orderItemService).createOrderItems(request.items(), orderProduct);
        verify(orderService).saveOrder(request, orderItems);
    }

    @Test
    @DisplayName("주문한 상품의 수량을 수정한다.")
    void modify_ordered_product_quantity() {
        // given
        Order order = getOrderWithId();
        List<OrderProductQuantityDto> items = getOrderProductQuantityDtos();
        UpdateOrderRequest request = new UpdateOrderRequest(order.getEmail(), items);

        // when
        when(orderService.getOrderForUpdate(anyString(), anyString())).thenReturn(order);
        orderUseCase.modifyProductQuantityOfOrder(order.getId().toString(), request);

        // then
        verify(orderService).getOrderForUpdate(order.getId().toString(), order.getEmail());
        verify(orderItemService).modifyOrderItems(order.getId(), items);
    }
}

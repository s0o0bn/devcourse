package com.devcourse.gc_coffee.order.service;

import com.devcourse.gc_coffee.global.exception.BadRequestException;
import com.devcourse.gc_coffee.order.domain.OrderItem;
import com.devcourse.gc_coffee.order.dto.OrderProductQuantityDto;
import com.devcourse.gc_coffee.order.repository.OrderItemRepository;
import com.devcourse.gc_coffee.product.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.devcourse.gc_coffee.fixture.order.OrderItemFixture.getOrderItems;
import static com.devcourse.gc_coffee.fixture.order.OrderRequestFixture.getOrderProductQuantityDtos;
import static com.devcourse.gc_coffee.fixture.product.ProductFixture.getProductWithId;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderItemServiceTest {
    @InjectMocks
    private OrderItemService orderItemService;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Test
    @DisplayName("주문한 상품에 따라 OrderItem 리스트를 생성한다.")
    void create_order_items_of_ordered_products() {
        // given
        Product product1 = getProductWithId();
        Product product2 = getProductWithId();
        List<OrderProductQuantityDto> dtos = List.of(
                new OrderProductQuantityDto(product1.getId().toString(), 1),
                new OrderProductQuantityDto(product2.getId().toString(), 2)
        );
        Map<String, Product> orderProducts = Map.of(product1.getId().toString(), product1, product2.getId().toString(), product2);

        // when
        List<OrderItem> orderItems = orderItemService.createOrderItems(dtos, orderProducts);

        // then
        assertEquals(dtos.size(), orderItems.size());
    }

    @Test
    @DisplayName("주문한 상품이 없을 때 예외가 발생한다.")
    void exception_thrown_when_order_not_exists_product() {
        // given
        List<OrderProductQuantityDto> dtos = getOrderProductQuantityDtos();
        Map<String, Product> emptyProducts = new HashMap<>();

        // when

        // then
        assertThrows(NoSuchElementException.class, () -> orderItemService.createOrderItems(dtos, emptyProducts));
    }

    @Test
    @DisplayName("주문한 상품의 수량을 수정한다.")
    void modify_ordered_product_quantity() {
        // given
        List<OrderItem> orderItems = getOrderItems();
        String productId = orderItems.get(0).getProduct().getId().toString();
        List<OrderProductQuantityDto> dtos = List.of(new OrderProductQuantityDto(productId, 1));

        // when
        when(orderItemRepository.findAllByOrderId(any(UUID.class))).thenReturn(orderItems);
        orderItemService.modifyOrderItems(UUID.randomUUID(), dtos);

        // then
        verify(orderItemRepository).saveAll(anyList());
    }

    @Test
    @DisplayName("주문하지 않은 상품의 수량을 수정할 시 예외가 발생한다.")
    void exception_thrown_when_modify_product_quantity_not_ordered() {
        // given
        List<OrderItem> orderItems = getOrderItems();
        String productId = "not ordered product";
        List<OrderProductQuantityDto> dtos = List.of(new OrderProductQuantityDto(productId, 1));

        // when
        when(orderItemRepository.findAllByOrderId(any(UUID.class))).thenReturn(orderItems);

        // then
        assertThrows(BadRequestException.class,
                () -> orderItemService.modifyOrderItems(UUID.randomUUID(), dtos));
    }
}

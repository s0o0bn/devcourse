package com.devcourse.gc_coffee.order.service;

import com.devcourse.gc_coffee.order.domain.OrderItem;
import com.devcourse.gc_coffee.order.dto.OrderProductQuantityDto;
import com.devcourse.gc_coffee.product.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.devcourse.gc_coffee.fixture.order.OrderRequestFixture.getOrderProductQuantityDtos;
import static com.devcourse.gc_coffee.fixture.product.ProductFixture.getProductWithId;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class OrderItemServiceTest {
    @InjectMocks
    private OrderItemService orderItemService;

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
}

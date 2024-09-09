package com.devcourse.gc_coffee.fixture.order;

import com.devcourse.gc_coffee.order.domain.OrderItem;
import com.devcourse.gc_coffee.product.domain.Product;

import java.util.List;

import static com.devcourse.gc_coffee.fixture.product.ProductFixture.getProductWithId;

public class OrderItemFixture {

    public static List<OrderItem> getOrderItems() {
        Product product1 = getProductWithId();
        Product product2 = getProductWithId();
        OrderItem item1 = new OrderItem(product1, 1);
        OrderItem item2 = new OrderItem(product2, 2);

        return List.of(item1, item2);
    }
}

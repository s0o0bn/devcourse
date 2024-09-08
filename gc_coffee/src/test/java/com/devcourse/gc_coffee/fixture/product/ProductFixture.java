package com.devcourse.gc_coffee.fixture.product;

import com.devcourse.gc_coffee.product.domain.Product;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProductFixture {
    public static final int PRODUCT_LIST_FIXTURE_SIZE = 3;

    public static Product getProduct() {
        return Product.builder()
                .name("name")
                .category("category")
                .price(15000)
                .build();
    }

    public static Product getProductWithId() {
        Product product = getProduct();

        try {
            Field field = Product.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(product, UUID.randomUUID());
        } catch (Exception ignored) {}

        return product;
    }

    public static Product getProductWithTimestamp() {
        Product product = getProductWithId();

        try {
            Class<?> superclass = product.getClass().getSuperclass();
            Field field = superclass.getDeclaredField("createdAt");
            field.setAccessible(true);
            field.set(product, LocalDateTime.now());
        } catch (Exception ignored) {}

        return product;
    }
}

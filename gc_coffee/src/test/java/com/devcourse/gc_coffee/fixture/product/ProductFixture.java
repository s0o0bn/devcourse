package com.devcourse.gc_coffee.fixture.product;

import com.devcourse.gc_coffee.product.domain.Product;

public class ProductFixture {
    public static Product getProduct() {
        return Product.builder()
                .name("name")
                .category("category")
                .price(15000)
                .build();
    }
}

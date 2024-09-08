package com.devcourse.gc_coffee.fixture.product;

import com.devcourse.gc_coffee.product.dto.request.ProductRequest;

public class ProductRequestFixture {
    public static ProductRequest getProductRequest() {
        return new ProductRequest("name", "category", 10000, null);
    }

    public static ProductRequest getOtherProductRequest() {
        return new ProductRequest("name 2", "category", 15000, null);
    }
}

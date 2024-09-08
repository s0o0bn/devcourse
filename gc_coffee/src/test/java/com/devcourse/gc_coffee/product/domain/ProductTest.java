package com.devcourse.gc_coffee.product.domain;

import com.devcourse.gc_coffee.product.dto.request.ProductRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.devcourse.gc_coffee.fixture.product.ProductFixture.getProduct;
import static com.devcourse.gc_coffee.fixture.product.ProductRequestFixture.getOtherProductRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {
    @Test
    @DisplayName("상품 정보를 수정한다.")
    void update_product_details() {
        // given
        Product product = getProduct();
        ProductRequest requestForUpdate = getOtherProductRequest();

        // when
        product.update(requestForUpdate);

        // then
        assertEquals(product.getName(), requestForUpdate.name());
        assertEquals(product.getCategory(), requestForUpdate.category());
        assertEquals(product.getPrice(), requestForUpdate.price());
        assertEquals(product.getDescription(), requestForUpdate.description());
    }
}

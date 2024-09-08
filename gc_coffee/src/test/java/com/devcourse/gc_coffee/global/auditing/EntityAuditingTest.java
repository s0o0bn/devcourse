package com.devcourse.gc_coffee.global.auditing;

import com.devcourse.gc_coffee.product.domain.Product;
import com.devcourse.gc_coffee.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.devcourse.gc_coffee.fixture.product.ProductFixture.getProduct;
import static com.devcourse.gc_coffee.fixture.product.ProductRequestFixture.getOtherProductRequest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
class EntityAuditingTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("엔티티 INSERT 시 생성 일시가 함께 저장된다.")
    void when_save_new_entity_then_created_at_saved_too() {
        // given
        Product product = getProduct();

        // when
        productRepository.saveAndFlush(product);

        // then
        assertNotNull(product.getCreatedAt());
        assertNull(product.getUpdatedAt());
    }

    @Test
    @DisplayName("엔티티 UPDATE 시 수정 일시가 함께 저장된다.")
    void when_modify_entity_then_updated_at_saved_too() {
        // given
        Product product = getProduct();
        productRepository.save(product);
        product.update(getOtherProductRequest());

        // when
        productRepository.saveAndFlush(product);

        // then
        assertNotNull(product.getUpdatedAt());
    }
}

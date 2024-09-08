package com.devcourse.gc_coffee.admin.product;

import com.devcourse.gc_coffee.product.domain.Product;
import com.devcourse.gc_coffee.product.dto.request.ProductRequest;
import com.devcourse.gc_coffee.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static com.devcourse.gc_coffee.fixture.product.ProductRequestFixture.getProductRequest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductManageServiceTest {
    @InjectMocks
    private ProductManageService productManageService;

    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("새 상품을 추가한다.")
    void create_new_product() {
        // given
        ProductRequest request = getProductRequest();

        // when
        productManageService.addNewProduct(request);

        // then
        verify(productRepository).save(any(Product.class));
    }

    @Test
    @DisplayName("상품 정보를 수정한다.")
    void update_product_details() {
        // given
        String id = UUID.randomUUID().toString();
        ProductRequest request = getProductRequest();
        Product product = request.toEntity();

        // when
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.of(product));
        productManageService.modifyProduct(id, request);

        // then
        verify(productRepository).save(product);
    }

    @Test
    @DisplayName("존재하지 않는 상품을 수정할 시 예외가 발생한다.")
    void exception_thrown_when_modify_not_exists_product() {
        // given
        String id = UUID.randomUUID().toString();
        ProductRequest request = getProductRequest();

        // when
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // then
        assertThrows(NoSuchElementException.class, () -> productManageService.modifyProduct(id, request));
    }

    @Test
    @DisplayName("상품을 삭제한다.")
    void delete_product() {
        // given
        String id = UUID.randomUUID().toString();

        // when
        productManageService.deleteProduct(id);

        // then
        verify(productRepository).deleteById(any(UUID.class));
    }
}

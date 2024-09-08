package com.devcourse.gc_coffee.product.service;

import com.devcourse.gc_coffee.product.domain.Product;
import com.devcourse.gc_coffee.product.dto.ProductDetailDto;
import com.devcourse.gc_coffee.product.dto.ProductDto;
import com.devcourse.gc_coffee.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.devcourse.gc_coffee.fixture.product.ProductFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductReadServiceTest {
    @InjectMocks
    private ProductReadService productReadService;

    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("상품의 ID로 상품 정보를 조회한다.")
    void get_product_infos_with_id() {
        // given
        String id = UUID.randomUUID().toString();
        Product product = getProductWithId();
        ProductDto expected = ProductDto.from(product);

        // when
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.of(product));
        ProductDto actual = productReadService.getProductWithoutTimestamp(id);

        // then
        verify(productRepository).findById(any(UUID.class));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("존재하지 않는 상품 조회 시 예외가 발생한다.")
    void exception_thrown_when_get_not_exists_product() {
        // given
        String id = UUID.randomUUID().toString();

        // when
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // then
        assertThrows(NoSuchElementException.class, () -> productReadService.getProductWithoutTimestamp(id));
        verify(productRepository).findById(any(UUID.class));
    }

    @Test
    @DisplayName("생성일시, 수정일시 등이 포함된 상품 정보 리스트를 조회한다.")
    void get_product_list_with_timestamp() {
        // given
        List<Product> products = Collections.nCopies(PRODUCT_LIST_FIXTURE_SIZE, getProductWithTimestamp());

        // when
        when(productRepository.findAll()).thenReturn(products);
        List<ProductDetailDto> actual = productReadService.getProductsWithTimestamp();

        // then
        verify(productRepository).findAll();
        assertEquals(PRODUCT_LIST_FIXTURE_SIZE, actual.size());
    }

    @Test
    @DisplayName("생성일시, 수정일시 등을 조회할 때 상품이 없으면 조회되지 않는다.")
    void get_empty_product_list_with_timestamp() {
        // given

        // when
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        List<ProductDetailDto> actual = productReadService.getProductsWithTimestamp();

        // then
        verify(productRepository).findAll();
        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("상품의 기본 정보를 포함한 상품 리스트를 조회한다.")
    void get_product_list_without_timestamp() {
        // given
        List<Product> products = Collections.nCopies(PRODUCT_LIST_FIXTURE_SIZE, getProductWithId());

        // when
        when(productRepository.findAll()).thenReturn(products);
        List<ProductDto> actual = productReadService.getProductsWithoutTimestamp();

        // then
        verify(productRepository).findAll();
        assertEquals(PRODUCT_LIST_FIXTURE_SIZE, actual.size());
    }

    @Test
    @DisplayName("상품 기본 정보 조회 시 상품이 없으면 조회되지 않는다.")
    void get_empty_product_list_without_timestamp() {
        // given

        // when
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        List<ProductDto> actual = productReadService.getProductsWithoutTimestamp();

        // then
        verify(productRepository).findAll();
        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("상품의 ID를 통해 조회 후, 각 상품 별 ID로 매핑한다.")
    void get_products_by_id_list_and_map_by_product_id() {
        // given
        Product product1 = getProductWithId();
        Product product2 = getProductWithId();
        List<UUID> ids = List.of(product1.getId(), product2.getId());

        // when
        when(productRepository.findAllById(ids)).thenReturn(List.of(product1, product2));
        Map<String, Product> actual = productReadService.mapProductWithId(ids);

        // then
        assertEquals(ids.size(), actual.size());
        assertTrue(actual.containsKey(product1.getId().toString()));
        assertTrue(actual.containsKey(product2.getId().toString()));
    }
}

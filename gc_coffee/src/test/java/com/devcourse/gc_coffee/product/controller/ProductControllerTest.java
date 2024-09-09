package com.devcourse.gc_coffee.product.controller;

import com.devcourse.gc_coffee.product.dto.ProductDto;
import com.devcourse.gc_coffee.product.service.ProductReadService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductReadService productReadService;

    @Nested
    @DisplayName("[상품 조회 테스트]")
    class GetAllProductsTest {
        @Test
        @DisplayName("전체 상품 목록을 조회한다.")
        void get_all_product_list() throws Exception {
            // given
            ProductDto dto1 = new ProductDto("id1", "product1", "category", 10000, null);
            ProductDto dto2 = new ProductDto("id2", "product2", "category", 15000, null);
            List<ProductDto> products = List.of(dto1, dto2);

            // when
            when(productReadService.getProductsWithoutTimestamp()).thenReturn(products);

            // then
            mvc.perform(get("/products")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.products", hasSize(2)))
                    .andExpect(jsonPath("$.products[0].id").value(dto1.id()))
                    .andExpect(jsonPath("$.products[0].name").value(dto1.name()))
                    .andExpect(jsonPath("$.products[1].id").value(dto2.id()))
                    .andExpect(jsonPath("$.products[1].name").value(dto2.name()));
            verify(productReadService).getProductsWithoutTimestamp();
        }

        @Test
        @DisplayName("상품 목록이 없을 때 빈 리스트가 응답된다.")
        void get_empty_product_list() throws Exception {
            // given

            // when
            when(productReadService.getProductsWithoutTimestamp()).thenReturn(Collections.emptyList());

            // then
            mvc.perform(get("/products")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.products", hasSize(0)));
            verify(productReadService).getProductsWithoutTimestamp();
        }
    }
}

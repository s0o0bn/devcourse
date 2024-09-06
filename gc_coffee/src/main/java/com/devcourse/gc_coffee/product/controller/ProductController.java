package com.devcourse.gc_coffee.product.controller;

import com.devcourse.gc_coffee.product.dto.response.ProductListResponse;
import com.devcourse.gc_coffee.product.service.ProductReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductReadService service;

    @GetMapping("")
    public ResponseEntity<ProductListResponse> getProducts() {
        ProductListResponse response = new ProductListResponse(service.getProductsWithoutTimestamp());
        return ResponseEntity.ok(response);
    }
}

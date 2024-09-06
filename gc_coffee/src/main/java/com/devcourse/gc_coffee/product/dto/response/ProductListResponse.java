package com.devcourse.gc_coffee.product.dto.response;

import com.devcourse.gc_coffee.product.dto.ProductDto;

import java.util.List;

public record ProductListResponse(List<ProductDto> products) {
}

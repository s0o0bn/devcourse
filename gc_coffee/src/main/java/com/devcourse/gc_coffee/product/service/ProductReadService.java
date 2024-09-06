package com.devcourse.gc_coffee.product.service;

import com.devcourse.gc_coffee.product.domain.Product;
import com.devcourse.gc_coffee.product.repository.ProductRepository;
import com.devcourse.gc_coffee.product.dto.ProductDto;
import com.devcourse.gc_coffee.product.dto.ProductDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductReadService {
    private final ProductRepository repository;

    public ProductDto getProductWithoutTimestamp(String id) {
        Product product = repository.findById(UUID.fromString(id))
                .orElseThrow(NoSuchElementException::new);
        return ProductDto.from(product);
    }

    public List<ProductDetailDto> getProductsWithTimestamp() {
        return repository.findAll().stream()
                .map(ProductDetailDto::from)
                .toList();
    }
}

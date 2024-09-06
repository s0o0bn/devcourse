package com.devcourse.gc_coffee.product.service;

import com.devcourse.gc_coffee.product.dto.ProductDetailDto;
import com.devcourse.gc_coffee.product.dto.ProductDto;
import com.devcourse.gc_coffee.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductReadService {
    private final ProductRepository repository;

    public ProductDto getProductWithoutTimestamp(String id) {
        return repository.findById(UUID.fromString(id))
                .map(ProductDto::from)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<ProductDetailDto> getProductsWithTimestamp() {
        return repository.findAll().stream()
                .map(ProductDetailDto::from)
                .toList();
    }

    public List<ProductDto> getProductsWithoutTimestamp() {
        return repository.findAll().stream()
                .map(ProductDto::from)
                .toList();
    }
}

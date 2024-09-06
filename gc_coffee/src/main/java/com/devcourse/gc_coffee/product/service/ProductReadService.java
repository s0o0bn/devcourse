package com.devcourse.gc_coffee.product.service;

import com.devcourse.gc_coffee.product.domain.Product;
import com.devcourse.gc_coffee.product.dto.ProductDetailDto;
import com.devcourse.gc_coffee.product.dto.ProductDto;
import com.devcourse.gc_coffee.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductReadService {
    private final ProductRepository productRepository;

    public ProductDto getProductWithoutTimestamp(String id) {
        return productRepository.findById(UUID.fromString(id))
                .map(ProductDto::from)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<ProductDetailDto> getProductsWithTimestamp() {
        return productRepository.findAll().stream()
                .map(ProductDetailDto::from)
                .toList();
    }

    public List<ProductDto> getProductsWithoutTimestamp() {
        return productRepository.findAll().stream()
                .map(ProductDto::from)
                .toList();
    }

    public Map<String, Product> mapProductWithId(List<UUID> productIds) {
        return productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(product -> product.getId().toString(), product -> product));
    }
}

package com.devcourse.gc_coffee.service.product;

import com.devcourse.gc_coffee.domain.product.Product;
import com.devcourse.gc_coffee.domain.product.ProductRepository;
import com.devcourse.gc_coffee.service.product.dto.ProductBasicDto;
import com.devcourse.gc_coffee.service.product.dto.ProductDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductReadService {
    private final ProductRepository repository;

    public ProductBasicDto getProductWithoutTimestamp(String id) {
        Product product = repository.findById(UUID.fromString(id))
                .orElseThrow(NoSuchElementException::new);
        return ProductBasicDto.from(product);
    }

    public List<ProductDetailDto> getProductsWithTimestamp() {
        return repository.findAll().stream()
                .map(ProductDetailDto::from)
                .toList();
    }
}

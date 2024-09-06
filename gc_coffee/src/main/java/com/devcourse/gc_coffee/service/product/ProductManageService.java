package com.devcourse.gc_coffee.service.product;

import com.devcourse.gc_coffee.domain.product.Product;
import com.devcourse.gc_coffee.domain.product.ProductRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 관리자만
@Service
@Transactional
@RequiredArgsConstructor
public class ProductManageService {
    private final ProductRepository repository;

    // C
    public void addNewProduct(@NotNull ProductDto dto) {
        Product product = dto.toEntity();
        repository.save(product);
    }

    // U

    // D
}

package com.devcourse.gc_coffee.service.product;

import com.devcourse.gc_coffee.domain.product.Product;
import com.devcourse.gc_coffee.domain.product.ProductRepository;
import com.devcourse.gc_coffee.service.product.dto.ProductDto;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

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
    public void modifyProduct(@NotNull String id, @NotNull ProductDto dto) {
        Product product = repository.findById(UUID.fromString(id))
                .orElseThrow(NoSuchElementException::new);
        product.update(dto);
        repository.save(product);
    }

    // D
    public void deleteProduct(String id) {
        repository.deleteById(UUID.fromString(id));
    }
}

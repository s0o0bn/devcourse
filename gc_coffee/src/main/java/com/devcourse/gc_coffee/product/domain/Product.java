package com.devcourse.gc_coffee.product.domain;

import com.devcourse.gc_coffee.global.auditing.BaseEntity;
import com.devcourse.gc_coffee.order.domain.OrderItem;
import com.devcourse.gc_coffee.product.dto.request.ProductRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_name")
    private String name;
    private String category;
    private long price;
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Product(String name, String category, long price, String description) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public void update(ProductRequest dto) {
        this.name = dto.name();
        this.category = dto.category();
        this.price = dto.price();
        this.description = dto.description();
    }
}



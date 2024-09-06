package com.devcourse.gc_coffee.domain.order;

import com.devcourse.gc_coffee.common.domain.BaseEntity;
import com.devcourse.gc_coffee.domain.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String category;
    private long price;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Builder
    public OrderItem(String category, long price, int quantity) {
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
}

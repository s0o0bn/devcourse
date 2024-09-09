package com.devcourse.gc_coffee.order.domain;

import com.devcourse.gc_coffee.global.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email;
    private String address;
    private String postcode;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Order(String email, String address, String postcode, OrderStatus orderStatus) {
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = orderStatus;
    }

    public long getTotalPrice() {
        return orderItems.stream()
                .mapToLong(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    public boolean isOrderedBy(String email) {
        return this.email.equals(email);
    }

    public boolean isModifiable() {
        return this.orderStatus.equals(OrderStatus.PAYMENT_COMPLETED);
    }

    public void cancel() {
        this.orderStatus = OrderStatus.CANCELED;
    }
}

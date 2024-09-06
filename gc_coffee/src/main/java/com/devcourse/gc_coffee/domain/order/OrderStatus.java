package com.devcourse.gc_coffee.domain.order;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    PAYMENT_COMPLETED("결제 완료"),
    IN_PROGRESS("배송중"),
    COMPLETED("배송 완료"),
    CANCELED("주문 취소");

    private final String status;
}

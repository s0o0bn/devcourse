package com.devcourse.gc_coffee.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PAYMENT_COMPLETED("결제 완료"),
    IN_PROGRESS("배송중"),
    COMPLETED("배송 완료"),
    CANCELED("주문 취소");

    private final String status;
}

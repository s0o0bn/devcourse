package com.devcourse.gc_coffee.order.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderExceptionType {
    UNAUTHORIZED("This order is not yours."),
    IS_NOT_MODIFIABLE("This order is already processed."),
    NOT_ORDERED_PRODUCT("You didn't order this product");

    private final String message;
}

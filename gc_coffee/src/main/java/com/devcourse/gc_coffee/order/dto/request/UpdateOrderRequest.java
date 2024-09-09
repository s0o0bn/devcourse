package com.devcourse.gc_coffee.order.dto.request;

import com.devcourse.gc_coffee.order.dto.OrderProductQuantityDto;
import jakarta.validation.constraints.Email;

import java.util.List;

public record UpdateOrderRequest(@Email String email,
                                 List<OrderProductQuantityDto> items) {
}

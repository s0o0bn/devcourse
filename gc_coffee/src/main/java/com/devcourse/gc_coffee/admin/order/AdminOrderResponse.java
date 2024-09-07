package com.devcourse.gc_coffee.admin.order;

import com.devcourse.gc_coffee.order.dto.OrderDto;

import java.util.List;

public record AdminOrderResponse(List<OrderDto> today, List<OrderDto> others) {
}

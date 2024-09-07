package com.devcourse.gc_coffee.order.dto.response;

import com.devcourse.gc_coffee.order.dto.OrderBasicDto;

import java.util.List;

public record OrderListResponse(List<OrderBasicDto> orders) {
}

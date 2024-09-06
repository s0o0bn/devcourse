package com.devcourse.gc_coffee.order.dto;

import jakarta.validation.constraints.NotBlank;

public record OrderProductQuantityDto(@NotBlank String productId, int quantity) {
}

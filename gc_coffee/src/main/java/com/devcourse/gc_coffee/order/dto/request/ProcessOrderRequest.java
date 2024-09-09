package com.devcourse.gc_coffee.order.dto.request;

import java.util.List;
import java.util.UUID;

public record ProcessOrderRequest(List<String> ids) {
    public List<UUID> getOrderIds() {
        return ids.stream()
                .map(UUID::fromString)
                .toList();
    }
}

package com.devcourse.gc_coffee.order.controller;

import com.devcourse.gc_coffee.order.dto.request.OrderRequest;
import com.devcourse.gc_coffee.order.dto.response.OrderListResponse;
import com.devcourse.gc_coffee.order.service.OrderReadService;
import com.devcourse.gc_coffee.order.service.facade.CreateOrderUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final OrderReadService orderReadService;

    @PostMapping("")
    public ResponseEntity<Void> orderProducts(@RequestBody @Valid OrderRequest request) {
        createOrderUseCase.createOrder(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<OrderListResponse> getOrdersOf(@RequestParam @Valid @NotBlank String email) {
        OrderListResponse response = new OrderListResponse(orderReadService.getOrdersOf(email));
        return ResponseEntity.ok(response);
    }
}

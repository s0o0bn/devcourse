package com.devcourse.gc_coffee.order.controller;

import com.devcourse.gc_coffee.order.dto.request.OrderRequest;
import com.devcourse.gc_coffee.order.service.facade.CreateOrderUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;

    @PostMapping("")
    public ResponseEntity<Void> orderProducts(@RequestBody @Valid OrderRequest request) {
        createOrderUseCase.createOrder(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

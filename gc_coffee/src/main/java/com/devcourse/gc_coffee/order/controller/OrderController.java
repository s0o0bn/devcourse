package com.devcourse.gc_coffee.order.controller;

import com.devcourse.gc_coffee.order.dto.request.OrderRequest;
import com.devcourse.gc_coffee.order.dto.request.UpdateOrderRequest;
import com.devcourse.gc_coffee.order.dto.response.OrderListResponse;
import com.devcourse.gc_coffee.order.service.OrderReadService;
import com.devcourse.gc_coffee.order.service.OrderService;
import com.devcourse.gc_coffee.order.service.facade.OrderUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderUseCase orderUseCase;
    private final OrderReadService orderReadService;
    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<Void> orderProducts(@RequestBody @Valid OrderRequest request) {
        orderUseCase.createOrder(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<OrderListResponse> getOrdersOf(@RequestParam @Valid @Email String email) {
        OrderListResponse response = new OrderListResponse(orderReadService.getOrdersOf(email));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> modifyOrderProductQuantity(@PathVariable("id") String id,
                                                           @RequestBody @Valid UpdateOrderRequest request) {
        orderUseCase.modifyProductQuantityOfOrder(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") String id,
                                            @RequestParam @Valid @Email String email) {
        orderService.deleteOrder(id, email);
        return ResponseEntity.ok().build();
    }
}

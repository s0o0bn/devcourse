package com.devcourse.gc_coffee.admin.order;

import com.devcourse.gc_coffee.order.dto.request.ProcessOrderRequest;
import com.devcourse.gc_coffee.order.service.OrderReadService;
import com.devcourse.gc_coffee.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {
    private final OrderReadService orderReadService;
    private final OrderService orderService;

    @GetMapping("")
    public ModelAndView getAllOrders() {
        ModelAndView mav = new ModelAndView("/admin/order/list");
        mav.addObject("orders", orderReadService.getAllOrders());

        return mav;
    }

    @PutMapping("")
    @ResponseBody
    public ResponseEntity<Void> processOrders(ProcessOrderRequest request) {
        orderService.processOrders(request);
        return ResponseEntity.ok().build();
    }
}

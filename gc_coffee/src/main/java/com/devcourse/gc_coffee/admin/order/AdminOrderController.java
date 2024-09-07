package com.devcourse.gc_coffee.admin.order;

import com.devcourse.gc_coffee.order.service.OrderReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {
    private final OrderReadService orderReadService;

    @GetMapping("")
    public ModelAndView getAllOrders() {
        ModelAndView mav = new ModelAndView("/admin/order/list");
        mav.addObject("orders", orderReadService.getAllOrders());

        return mav;
    }
}

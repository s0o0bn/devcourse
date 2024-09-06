package com.devcourse.gc_coffee.web.admin;

import com.devcourse.gc_coffee.service.product.ProductDto;
import com.devcourse.gc_coffee.service.product.ProductManageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductManageService service;

    @GetMapping("/new")
    public String getProductCreationPage() {
        return "admin/product/new-product";
    }

    @PostMapping("")
    public String addNewProduct(@RequestBody @Valid ProductDto dto) {
        service.addNewProduct(dto);
        return "redirect:/admin/products";
    }

    @GetMapping("")
    public String getAllProducts() {
        return "admin/main";
    }
}

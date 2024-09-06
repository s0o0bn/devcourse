package com.devcourse.gc_coffee.web.admin;

import com.devcourse.gc_coffee.service.product.ProductBasicDto;
import com.devcourse.gc_coffee.service.product.ProductDto;
import com.devcourse.gc_coffee.service.product.ProductManageService;
import com.devcourse.gc_coffee.service.product.ProductReadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductManageService manageService;
    private final ProductReadService readService;

    @GetMapping("/new")
    public String getProductCreationPage() {
        return "admin/product/new-product";
    }

    @GetMapping("/{id}/update")
    public ModelAndView getProductModifyingPage(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("admin/product/update-product");
        ProductBasicDto dto = readService.getProductWithoutTimestamp(id);
        mav.addObject("product", dto);

        return mav;
    }

    @GetMapping("")
    public String getAllProducts() {
        return "admin/main";
    }

    @PostMapping("")
    public String addNewProduct(@RequestBody @Valid ProductDto dto) {
        manageService.addNewProduct(dto);
        return "redirect:/admin/products";
    }

    @PutMapping("/{id}")
    public String modifyProduct(@PathVariable("id") String id, @RequestBody @Valid ProductDto dto) {
        manageService.modifyProduct(id, dto);
        return "redirect:/admin/products";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        manageService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

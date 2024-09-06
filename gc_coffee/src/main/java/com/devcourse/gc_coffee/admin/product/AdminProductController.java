package com.devcourse.gc_coffee.admin.product;

import com.devcourse.gc_coffee.product.service.ProductReadService;
import com.devcourse.gc_coffee.product.dto.ProductDto;
import com.devcourse.gc_coffee.product.dto.ProductDetailDto;
import com.devcourse.gc_coffee.product.dto.request.ProductRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {
    private final ProductManageService manageService;
    private final ProductReadService readService;

    @GetMapping("/new")
    public String getProductCreationPage() {
        return "admin/product/new-product";
    }

    @GetMapping("/{id}/update")
    public ModelAndView getProductModifyingPage(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("admin/product/update-product");
        ProductDto dto = readService.getProductWithoutTimestamp(id);
        mav.addObject("product", dto);

        return mav;
    }

    @GetMapping("")
    public ModelAndView getAllProducts() {
        ModelAndView mav = new ModelAndView("admin/product/list");
        List<ProductDetailDto> dtos = readService.getProductsWithTimestamp();
        mav.addObject("products", dtos);

        return mav;
    }

    @PostMapping("")
    public String addNewProduct(@RequestBody @Valid ProductRequest dto) {
        manageService.addNewProduct(dto);
        return "redirect:/admin/products";
    }

    @PutMapping("/{id}")
    public String modifyProduct(@PathVariable("id") String id, @RequestBody @Valid ProductRequest dto) {
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

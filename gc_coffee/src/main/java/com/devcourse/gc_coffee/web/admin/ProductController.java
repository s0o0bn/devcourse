package com.devcourse.gc_coffee.web.admin;

import com.devcourse.gc_coffee.service.product.*;
import com.devcourse.gc_coffee.service.product.dto.ProductBasicDto;
import com.devcourse.gc_coffee.service.product.dto.ProductDetailDto;
import com.devcourse.gc_coffee.service.product.dto.ProductDto;
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
    public ModelAndView getAllProducts() {
        ModelAndView mav = new ModelAndView("admin/product/list");
        List<ProductDetailDto> dtos = readService.getProductsWithTimestamp();
        mav.addObject("products", dtos);

        return mav;
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

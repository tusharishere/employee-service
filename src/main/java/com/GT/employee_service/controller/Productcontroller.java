package com.GT.employee_service.controller;

import com.GT.employee_service.entity.Product;
import com.GT.employee_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class Productcontroller {

    private final ProductService productService;

    // Pagination API
    @GetMapping("/pagination")
    public Page<Product> getProductsWithPagination(
            @RequestParam int page,
            @RequestParam int size) {

        return productService.getProductsWithPagination(page, size);
    }

    // Pagination + Sorting API
    @GetMapping("/pagination-sorting")
    public Page<Product> getProductsWithPaginationAndSorting(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return productService.getProductsWithPaginationAndSorting(
                page,
                size,
                sortBy,
                direction
        );
    }

    // Category Filter + Pagination
    @GetMapping("/category/{category}")
    public Page<Product> getProductsByCategory(
            @PathVariable String category,
            @RequestParam int page,
            @RequestParam int size) {

        return productService.getProductsByCategory(category, page, size);
    }
}

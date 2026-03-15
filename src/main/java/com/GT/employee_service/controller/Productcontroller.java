package com.GT.employee_service.controller;

import com.GT.employee_service.entity.Product;
import com.GT.employee_service.projection.ProductDTO;
import com.GT.employee_service.projection.ProductSummary;
import com.GT.employee_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return productService.getProductsByCategory(category, page, size, sortBy, direction);
    }

    @GetMapping("/summary")
    public List<ProductSummary> getSummary(@RequestParam String category) {
        return productService.getProductSummary(category);
    }

    @GetMapping("/dto")
    public List<ProductDTO> getDTOs() {
        return productService.getProductDTOs();
    }
}

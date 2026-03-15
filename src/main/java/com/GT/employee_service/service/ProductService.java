package com.GT.employee_service.service;

import com.GT.employee_service.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<Product> getProductsWithPagination(int page, int size);

    Page<Product> getProductsWithPaginationAndSorting(int page, int size, String sortBy, String direction);

    Page<Product> getProductsByCategory(String category, int page, int size);

}

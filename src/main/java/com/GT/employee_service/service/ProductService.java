package com.GT.employee_service.service;

import com.GT.employee_service.entity.Product;
import com.GT.employee_service.projection.ProductDTO;
import com.GT.employee_service.projection.ProductSummary;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Page<Product> getProductsWithPagination(int page, int size);

    Page<Product> getProductsWithPaginationAndSorting(int page, int size, String sortBy, String direction);

    Page<Product> getProductsByCategory(String category, int page, int size, String sortBy, String direction);

    List<ProductSummary> getProductSummary(String category);

    List<ProductDTO> getProductDTOs();

    <T> List<T> getDynamicProjection(String category, Class<T> type);

}

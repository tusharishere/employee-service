package com.GT.employee_service.service.Implementation;

import com.GT.employee_service.entity.Product;
import com.GT.employee_service.repository.ProductRepository;
import com.GT.employee_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<Product> getProductsWithPagination(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getProductsWithPaginationAndSorting(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getProductsByCategory(String category, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findByCategory(category, pageable);
    }
}

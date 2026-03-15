package com.GT.employee_service.repository;

import com.GT.employee_service.entity.Product;
import com.GT.employee_service.projection.ProductDTO;
import com.GT.employee_service.projection.ProductSummary;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    // Basic Queries

    List<Product> findByName(String name);
//    List<Product> findByCategory(String category);
    Optional<Product> findBySku(String sku);

    //  Comparison Queries

    List<Product> findByPriceLessThan(BigDecimal price);
    List<Product> findByQuantityGreaterThan(Integer quantity);


    //    Range Queries

    List<Product> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    //  String Queries

    List<Product> findByNameContaining(String keyword);
    List<Product> findByNameStartingWith(String prefix);
    List<Product> findByNameEndingWith(String suffix);
    List<Product> findByNameIgnoreCase(String name);

    //  Multiple Conditions

    List<Product> findByCategoryAndPriceGreaterThan(String category, BigDecimal price);
    List<Product> findByCategoryOrName(String category, String name);
    List<Product> findByCategoryAndPriceBetween(String category, BigDecimal min, BigDecimal max);

    // Null Handling

    List<Product> findByDescriptionIsNull();
    List<Product> findByDescriptionIsNotNull();

    //  Sorting Queries

    List<Product> findByCategoryOrderByPriceAsc(String category);
    List<Product> findByCategoryOrderByCreatedAtDesc(String category);

    Product findTopByOrderByPriceDesc();
    Product findTopByOrderByPriceAsc();


    //  Top / First Queries

    List<Product> findTop5ByOrderByPriceDesc();

    //  Boolean / Existence Queries

    boolean existsBySku(String sku);
    long countByCategory(String category);

    //   Complex Queries
    List<Product> findByCategoryAndPriceBetweenAndQuantityGreaterThan(
            String category,
            BigDecimal min,
            BigDecimal max,
            Integer quantity
    );
    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);

    List<Product> findByNameContainingIgnoreCase(String keyword);

    //    Pagination Queries (Very Important)
    Page<Product> findByCategory(String category, Pageable pageable);

    //    Dynamic Sorting
    List<Product> findByCategory(String category, Sort sort);

    //    Very Advanced Method
    Page<Product> findByCategoryAndPriceBetweenAndNameContainingIgnoreCase(
            String category,
            BigDecimal min,
            BigDecimal max,
            String keyword,
            Pageable pageable
    );

    // Query by Field

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    List<Product> findProductByName(@Param("name") String name);

    // LIKE Search Query

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword%")
    List<Product> searchProductByName(@Param("keyword") String keyword);

    //  Update Query

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.price = :price WHERE p.id = :id")
    int updateProductPrice(
            @Param("id") Long id,
            @Param("price") BigDecimal price
    );

    // Pagination Query

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    Page<Product> findProductsByCategory(
            @Param("category") String category,
            Pageable pageable
    );

    // Complex Search Query

    @Query("""
    SELECT p FROM Product p
    WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
    OR LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))
    OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<Product> searchProducts(@Param("keyword") String keyword);

    // Native Query
    @Query(value = "SELECT * FROM products WHERE price > :price", nativeQuery = true)
    List<Product> findProductsAbovePrice(@Param("price") BigDecimal price);

    // Very Advanced Query

    @Query("""
    SELECT p FROM Product p
    WHERE (:category IS NULL OR p.category = :category)
    AND (:minPrice IS NULL OR p.price >= :minPrice)
    AND (:maxPrice IS NULL OR p.price <= :maxPrice)
    """)
    List<Product> filterProducts(
            @Param("category") String category,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice
    );

    //   Interface Projection
    List<ProductSummary> findByCategory(String category);

    //   DTO Projection
    @Query("SELECT new com.GT.projection_demo.dto.ProductDTO(p.name, p.price) FROM Product p")
    List<ProductDTO> getProductDTOs();

    //  Dynamic Projection
    <T> List<T> findByCategory(String category, Class<T> type);
}

package com.GT.employee_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products",
        uniqueConstraints = {
        @UniqueConstraint(name = "uk_product_sku",columnNames = "sku")
        },
        indexes = {
        @Index(name = "idx_product_name",columnList = "name"),
                @Index(name = "idx_prdouct_categoy",columnList = "category")
        }
)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name",nullable = false,length = 50)
    private String name;

    @Column(name = "description",nullable = false,length = 40)
    private String description;

    @Column(name = "sku",nullable = false,length = 50)
    private String sku;

    @Column(name = "category",length = 20)
    private String category;

    @Column(name = "price",nullable = false)
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}

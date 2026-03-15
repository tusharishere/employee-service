package com.GT.employee_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "employees",
        catalog = "employee_catalog",
        schema = "hr",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_employee_email", columnNames = {"email"})
        },
        indexes = {
                @Index(name = "idx_employee_name",columnList = "name"),
                @Index(name = "idx_employee_department",columnList = "department")
        }
)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "name",nullable = false,length = 50)
    private String name;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "department",nullable = false,length = 60)
    private String department;

    @Column(name = "salary")
    private Double salary;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private  LocalDateTime updatedAt;
}

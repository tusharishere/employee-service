package com.GT.employee_service.projection;

import java.math.BigDecimal;

public class ProductDTO {

    private String name;
    private BigDecimal price;

    public ProductDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

package com.rayllanderson.kafka.producer.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private BigDecimal value;
    private Integer quantity;

    public Product toModel() {
        return new Product(name, value, quantity);
    }
}

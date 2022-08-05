package com.rayllanderson.kafka.producer.product;

import com.rayllanderson.kafka.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Double weight;

    public Product buildToAvro() {
        return new Product(UUID.randomUUID().toString(), name, price.doubleValue(), quantity, weight);
    }
}

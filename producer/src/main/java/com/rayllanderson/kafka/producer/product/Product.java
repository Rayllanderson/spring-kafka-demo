package com.rayllanderson.kafka.producer.product;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

    private final String id = UUID.randomUUID().toString();
    private String name;
    private BigDecimal value;
    private Integer quantity;

    public Product() {
    }

    public Product(String name, BigDecimal value, Integer quantity) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", value=" + value + ", quantity=" + quantity + '}';
    }
}

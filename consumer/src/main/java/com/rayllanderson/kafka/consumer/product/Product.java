package com.rayllanderson.kafka.consumer.product;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    private String id;
    private String name;
    private BigDecimal value;
    private Integer quantity;

    public Product() {
    }

    public Product(String id, String name, BigDecimal value, Integer quantity) {
        this.id = id;
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

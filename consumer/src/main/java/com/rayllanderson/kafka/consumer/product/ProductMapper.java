package com.rayllanderson.kafka.consumer.product;

import com.rayllanderson.kafka.consumer.product.domain.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

    public static Product toDomain(com.rayllanderson.kafka.Product productRecord) {
        return new Product(productRecord.getId().toString(),
                productRecord.getName().toString(),
                BigDecimal.valueOf(productRecord.getPrice()),
                productRecord.getQuantity(),
                productRecord.getWeight() == null ? null : BigDecimal.valueOf(productRecord.getWeight())
        );
    }
}

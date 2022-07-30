package com.rayllanderson.kafka.consumer.product;

import com.rayllanderson.kafka.consumer.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}

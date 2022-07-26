package com.rayllanderson.kafka.producer.product;

import com.rayllanderson.kafka.producer.kafka.producer.KafkaProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final KafkaProducer<Product> producer;

    public ProductController(KafkaProducer<Product> producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<Void> publish(@RequestBody Product product) {
        producer.send(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

package com.rayllanderson.kafka.producer.product;

import com.rayllanderson.kafka.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private static final String CORRELATION_ID_HEADER = "Correlation-Id";

    @PostMapping
    public ResponseEntity<Void> publish(@RequestBody ProductRequest request) {
        Product product = request.buildToAvro();
        productService.process(product);
        return ResponseEntity.status(HttpStatus.CREATED).header(CORRELATION_ID_HEADER, (String) product.getId()).build();
    }
}

package com.rayllanderson.kafka.producer.product;

public interface KafkaProducer {
    void send(Product product);
}

package com.rayllanderson.kafka.producer.kafka.producer;

public interface KafkaProducer<T> {
    void send(T t);
}

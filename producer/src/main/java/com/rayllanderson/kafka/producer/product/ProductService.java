package com.rayllanderson.kafka.producer.product;

import com.rayllanderson.kafka.Product;
import com.rayllanderson.kafka.producer.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.rayllanderson.kafka.producer.kafka.producer.ProducerRecordMapper.createProducerRecord;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    @Value("${spring.kafka.product.topic.name}")
    private String topic;
    private final KafkaProducer kafkaProducer;

    public void process(Product product) {
        log.info("Enviando {} para tópico={}", product.getId(), topic);
        this.kafkaProducer.publish(createProducerRecord(product, topic, product.getId().toString()));
    }
}

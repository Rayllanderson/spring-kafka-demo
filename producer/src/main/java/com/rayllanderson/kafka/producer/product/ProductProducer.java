package com.rayllanderson.kafka.producer.product;

import com.rayllanderson.kafka.Product;
import com.rayllanderson.kafka.producer.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaFailureCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductProducer implements KafkaProducer<Product> {

    private final KafkaTemplate<String, Product> producer;

    @Value("${spring.kafka.product.topic.name}")
    private String topic;

    @Override
    public void send(Product product) {
        log.info("Enviando {} para tópico={}", product.getId(), topic);

        var producerRecord = buildProducerRecord(product);

        this.producer.send(producerRecord).addCallback(successResult -> log.info("Produto {} enviado com sucesso, record={}", product.getId(), producerRecord),
                (KafkaFailureCallback<String, Product>) failure -> log.error("Erro enviando {}, record={}", product.getId(), failure.getFailedProducerRecord(), failure)
        );
    }

    private ProducerRecord<String, Product> buildProducerRecord(Product product) {
        var producerRecord = new ProducerRecord<>(topic, null, Instant.now().toEpochMilli(), String.valueOf(product.getId()), product);
        producerRecord.headers().add(KafkaHeaders.CORRELATION_ID, UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        return producerRecord;
    }
}

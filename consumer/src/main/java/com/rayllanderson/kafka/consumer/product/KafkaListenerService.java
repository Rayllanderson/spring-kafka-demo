package com.rayllanderson.kafka.consumer.product;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.CORRELATION_ID;

@Service
public class KafkaListenerService {

    private final Logger log = LoggerFactory.getLogger(KafkaListenerService.class);
    private final ProductRepository repository;

    public KafkaListenerService(ProductRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "${spring.kafka.product.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(@Payload ConsumerRecord<String, Product> product, @Header(CORRELATION_ID) String correlationId, Acknowledgment ack) {
        log.info("Mensagem recebida no tópico, {}, correlation-id={}", product, correlationId);

        repository.save(product.value());
        log.info("Produto {} salvo com sucesso", product.value().getId());

        ack.acknowledge();
    }
}

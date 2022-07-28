package com.rayllanderson.kafka.consumer.product;

import com.rayllanderson.kafka.consumer.kafka.consumer.KafkaConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductConsumer implements KafkaConsumer<String, Product> {

    private final ProductRepository repository;

    @Override
    @KafkaListener(topics = "${spring.kafka.product.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(@Payload ConsumerRecord<String, Product> product, Acknowledgment ack) {
        log.info("Mensagem recebida no tópico, {}", product);

        repository.save(product.value());
        log.info("Produto {} salvo com sucesso", product.value().getId());

        ack.acknowledge();
    }
}

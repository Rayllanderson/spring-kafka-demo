package com.rayllanderson.kafka.consumer.product;

import com.rayllanderson.kafka.consumer.kafka.consumer.KafkaConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import com.rayllanderson.kafka.Product;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductConsumer implements KafkaConsumer<String, Product> {

    private final ProductRepository repository;

    @Override
    @KafkaListener(topics = "${spring.kafka.product.topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "customKafkaListenerContainerFactory")
    public void listen(@Payload ConsumerRecord<String, Product> productRecord, Acknowledgment ack) {
        log.info("Mensagem recebida no tópico, {}", productRecord);

        repository.save(ProductMapper.toDomain(productRecord.value()));
        log.info("Produto {} salvo com sucesso", productRecord.value().getId());

        ack.acknowledge();
    }
}

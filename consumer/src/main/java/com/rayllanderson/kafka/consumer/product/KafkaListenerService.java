package com.rayllanderson.kafka.consumer.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    private final Logger log = LoggerFactory.getLogger(KafkaListenerService.class);
    private final ProductRepository repository;

    public KafkaListenerService(ProductRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "${spring.kafka.product.topic}")
    public void listen(Product product) {
        log.info("Recebendo mensagem e salvando {}", product);

        repository.save(product);

        log.info("Produto {} salvo com sucesso", product.getId());
    }
}

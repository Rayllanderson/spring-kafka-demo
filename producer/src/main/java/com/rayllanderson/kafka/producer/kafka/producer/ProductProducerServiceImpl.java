package com.rayllanderson.kafka.producer.kafka.producer;

import com.rayllanderson.kafka.producer.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaFailureCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductProducerServiceImpl implements KafkaProducer<Product> {

    private final KafkaTemplate<String, Product> producer;
    private final Logger log = LoggerFactory.getLogger(ProductProducerServiceImpl.class);

    @Value("${spring.kafka.product.topic.name}")
    private String topic;

    public ProductProducerServiceImpl(KafkaTemplate<String, Product> producer) {
        this.producer = producer;
    }

    @Override
    public void send(Product product) {
        log.info("Enviando {} para tópico={}", product.getId(), topic);
        this.producer.send(topic, product).addCallback(successResult ->
                        log.info("Produto {} enviado com sucesso, topic={}, partition={}, offset={}",
                                product.getId(),
                                topic,
                                successResult.getProducerRecord().partition(),
                                successResult.getRecordMetadata().offset()),
                (KafkaFailureCallback<String, Product>) failure ->
                        log.error("Erro enviando {}, topic={}, message={}, producerRecord={}",
                                product.getId(),
                                topic,
                                failure.getMessage(),
                                failure.getFailedProducerRecord(),
                                failure)
        );
    }
}

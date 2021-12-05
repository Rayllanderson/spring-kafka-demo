package com.rayllanderson.kafka.producer.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService implements KafkaProducer{

    private final KafkaTemplate<String, Object> producer;
    private final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);

    @Value("${spring.kafka.topic.name}")
    private String topic;

    public KafkaProducerService(KafkaTemplate<String, Object> producer) {
        this.producer = producer;
    }

    @Override
    public void send(Product product) {
        try {
            log.info("Enviando {} para tópico={}", product, topic);

            var kafkaResult = this.producer.send(topic, product);
            var topicName = kafkaResult.get().getProducerRecord().topic();
            var partition = kafkaResult.get().getProducerRecord().partition();
            var offset = kafkaResult.get().getRecordMetadata().offset();
            log.info("Produto {} enviado com sucesso para tópico={}, partition={}, offset={}", product.getId(), topicName, partition, offset);
        } catch (Exception e){
            log.error("Erro enviando {} para tópico={}, {}", product, topic, e.getMessage());
        }
    }
}

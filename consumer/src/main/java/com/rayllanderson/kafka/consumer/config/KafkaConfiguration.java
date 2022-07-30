package com.rayllanderson.kafka.consumer.config;

import com.rayllanderson.kafka.consumer.product.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ErrorHandler;

import static org.springframework.kafka.listener.ContainerProperties.AckMode.MANUAL_IMMEDIATE;

@Configuration
public class KafkaConfiguration {
    private static final Logger log = LoggerFactory.getLogger(KafkaConfiguration.class);

    @Bean("customKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, Product> kafkaListenerContainerFactory(ConsumerFactory<String, Product> productConsumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, Product>();
        factory.setConsumerFactory(productConsumerFactory);
        factory.getContainerProperties().setAckMode(MANUAL_IMMEDIATE);
        factory.setErrorHandler(errorHandler());
        factory.setConcurrency(3);
        return factory;
    }

    private ErrorHandler errorHandler() {
        return (exception, consumerRecord) -> log.error("erro ao consumir mensagem, {}-{}", exception.getMessage(), consumerRecord, exception);
    }
}

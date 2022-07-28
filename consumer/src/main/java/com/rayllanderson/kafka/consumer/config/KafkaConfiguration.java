package com.rayllanderson.kafka.consumer.config;

import com.rayllanderson.kafka.consumer.product.Product;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import static org.springframework.kafka.listener.ContainerProperties.AckMode.MANUAL_IMMEDIATE;

@Configuration
public class KafkaConfiguration {
    private static final Logger log = LoggerFactory.getLogger(KafkaConfiguration.class);

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Product> kafkaListenerContainerFactory(ConsumerFactory<String, Product> productConsumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, Product>();
        factory.setConsumerFactory(createConsumerFactoryBasedOn(productConsumerFactory));
        factory.getContainerProperties().setAckMode(MANUAL_IMMEDIATE);
        factory.setErrorHandler(errorHandler());
        factory.setConcurrency(3);
        return factory;
    }

    private DefaultKafkaConsumerFactory<String, Product> createConsumerFactoryBasedOn(ConsumerFactory<String, Product> productConsumerFactory) {
        StringDeserializer stringDeserializer = new StringDeserializer();
        var jsonDeserializer = new JsonDeserializer<>(Product.class, false);
        return new DefaultKafkaConsumerFactory<>(productConsumerFactory.getConfigurationProperties(), stringDeserializer, jsonDeserializer);
    }

    private ErrorHandler errorHandler() {
        return (exception, consumerRecord) -> log.error("erro ao consumir mensagem, {}-{}", exception.getMessage(), consumerRecord, exception);
    }
}

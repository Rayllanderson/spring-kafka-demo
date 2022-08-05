package com.rayllanderson.kafka.consumer.config;

import org.apache.kafka.common.header.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static java.util.Arrays.stream;
import static org.springframework.kafka.listener.ContainerProperties.AckMode.MANUAL_IMMEDIATE;

@Configuration
public class KafkaConfiguration {
    private static final Logger log = LoggerFactory.getLogger(KafkaConfiguration.class);
    private static final String SCHEMA_NAME_HEADER_KEY = "schema_name";
    private static final String EMPTY = "";

    @Bean("customKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(ConsumerFactory<Object, Object> productConsumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(productConsumerFactory);
        factory.getContainerProperties().setAckMode(MANUAL_IMMEDIATE);
        factory.setErrorHandler(errorHandler());
        factory.setRecordFilterStrategy(getFilterStrategy());
        factory.setAckDiscarded(true);
        factory.setConcurrency(2);
        return factory;
    }

    private RecordFilterStrategy<Object, Object> getFilterStrategy() {
        return consumerRecord -> {
            var schemaName = Optional.ofNullable(consumerRecord.headers().lastHeader(SCHEMA_NAME_HEADER_KEY))
                    .map(Header::value)
                    .map(v -> new String(v, StandardCharsets.UTF_8))
                    .orElse(EMPTY);
            return stream(SupportedType.values()).noneMatch(supportedType -> supportedType.toString().equalsIgnoreCase(schemaName));
        };
    }

    private ErrorHandler errorHandler() {
        return (exception, consumerRecord) -> log.error("erro ao consumir mensagem, {}-{}", exception.getMessage(), consumerRecord, exception);
    }
}

package com.rayllanderson.kafka.producer.kafka.producer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.support.KafkaHeaders;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProducerRecordMapper {

    public static ProducerRecord<String, SpecificRecord> createProducerRecord(SpecificRecord schema, String topic, String key) {
        var producerRecord = new ProducerRecord<>(topic, null, Instant.now().toEpochMilli(), key, schema);
        producerRecord.headers().add(KafkaHeaders.CORRELATION_ID, key.getBytes(StandardCharsets.UTF_8));
        return producerRecord;
    }
}

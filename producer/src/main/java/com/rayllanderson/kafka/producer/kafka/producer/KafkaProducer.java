package com.rayllanderson.kafka.producer.kafka.producer;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerRecord;

public interface KafkaProducer {
    void publish(ProducerRecord<String, SpecificRecord> producerRecord);
}

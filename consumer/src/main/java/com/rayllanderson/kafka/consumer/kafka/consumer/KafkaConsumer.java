package com.rayllanderson.kafka.consumer.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

public interface KafkaConsumer<K, V> {

    void listen(ConsumerRecord<K, V> consumerRecord, Acknowledgment ack);
}

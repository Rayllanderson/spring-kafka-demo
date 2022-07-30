package com.rayllanderson.kafka.producer.payment;

import com.rayllanderson.kafka.producer.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.rayllanderson.kafka.producer.kafka.producer.ProducerRecordMapper.createProducerRecord;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    @Value("${spring.kafka.payment.topic.name}")
    private String topic;
    private final KafkaProducer kafkaProducer;

    public void process(PaymentRequest<?> payment) {
        log.info("Enviando {} para tópico={}", payment.getId(), topic);
        this.kafkaProducer.publish(createProducerRecord(payment.buildToAvro(), topic, payment.getId()));
    }
}

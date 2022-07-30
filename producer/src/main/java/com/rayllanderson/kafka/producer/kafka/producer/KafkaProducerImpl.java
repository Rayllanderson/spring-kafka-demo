package com.rayllanderson.kafka.producer.kafka.producer;

import com.rayllanderson.kafka.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaFailureCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {

    private final KafkaTemplate<String, SpecificRecord> producer;

    @Override
    public void publish(ProducerRecord<String, SpecificRecord> producerRecord) {
        var topic = producerRecord.topic();
        this.producer.send(producerRecord).addCallback(successResult -> log.info("Mensagem enviada com sucesso para o tópico '{}', record={}", topic, producerRecord),
                (KafkaFailureCallback<String, Product>) failure -> log.error("Erro enviando mensagem ao tópico '{}', record={}", topic, failure.getFailedProducerRecord(), failure)
        );
    }
}

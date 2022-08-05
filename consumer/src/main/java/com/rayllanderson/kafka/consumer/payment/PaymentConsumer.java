package com.rayllanderson.kafka.consumer.payment;

import com.rayllanderson.kafka.payment.PIX;
import com.rayllanderson.kafka.payment.TED;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.rayllanderson.kafka.consumer.payment.pix.PIX.fromRecord;
import static com.rayllanderson.kafka.consumer.payment.ted.TED.fromRecord;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(topics = "${spring.kafka.payment.topic}",
        clientIdPrefix = "${spring.kafka.payment.client-id}",
        groupId = "${spring.kafka.consumer.group-id}",
        containerFactory = "customKafkaListenerContainerFactory")
public class PaymentConsumer {

    private final PaymentService paymentService;

    @KafkaHandler
    public void listen(@Payload TED ted, Acknowledgment ack) {
        log.info("mensagem de 'ted' recebida no tópico, {}", ted);
        paymentService.process(fromRecord(ted));
        ack.acknowledge();
    }

    @KafkaHandler
    public void listen(@Payload PIX pix, Acknowledgment ack) {
        log.info("mensagem de 'pix' recebida no tópico, {}", pix);
        paymentService.process(fromRecord(pix));
        ack.acknowledge();
    }
}

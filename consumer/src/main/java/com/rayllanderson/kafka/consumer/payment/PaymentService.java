package com.rayllanderson.kafka.consumer.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final EntityManager entityManager;

    @Transactional
    void process(Payment payment) {
        entityManager.persist(payment);
    }

}

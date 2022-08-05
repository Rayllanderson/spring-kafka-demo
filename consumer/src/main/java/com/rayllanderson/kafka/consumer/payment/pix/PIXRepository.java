package com.rayllanderson.kafka.consumer.payment.pix;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PIXRepository extends JpaRepository<PIX, String> {
}

package com.rayllanderson.kafka.producer.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.apache.avro.specific.SpecificRecord;

import java.util.UUID;

@Getter
public abstract class PaymentRequest<T extends SpecificRecord> {
    @JsonIgnore
    private final String id = UUID.randomUUID().toString();

    public abstract T buildToAvro();
}

package com.rayllanderson.kafka.producer.payment.pix;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rayllanderson.kafka.payment.PIX;
import com.rayllanderson.kafka.producer.payment.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PixRequest extends PaymentRequest<PIX> {
    @JsonProperty("debit_key")
    private String debitKey;
    @JsonProperty("credit_key")
    private String creditKey;
    private Double value;

    @Override
    public PIX buildToAvro() {
        return new PIX(super.getId(), debitKey, creditKey, value);
    }
}

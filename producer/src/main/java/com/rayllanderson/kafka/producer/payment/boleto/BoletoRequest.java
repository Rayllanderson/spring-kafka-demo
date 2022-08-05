package com.rayllanderson.kafka.producer.payment.boleto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rayllanderson.kafka.payment.Boleto;
import com.rayllanderson.kafka.producer.payment.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoletoRequest extends PaymentRequest<Boleto> {
    @JsonProperty("bar_code")
    private String barCode;
    private Double value;

    @Override
    public Boleto buildToAvro() {
        return new Boleto(super.getId(), barCode, value);
    }
}

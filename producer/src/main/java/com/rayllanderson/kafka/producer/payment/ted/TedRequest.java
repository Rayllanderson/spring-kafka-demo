package com.rayllanderson.kafka.producer.payment.ted;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rayllanderson.kafka.payment.TED;
import com.rayllanderson.kafka.producer.payment.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TedRequest extends PaymentRequest<TED> {
    @JsonProperty("debit_account")
    private String debitAccount;
    @JsonProperty("credit_account")
    private String creditAccount;
    private Double value;
    private Double tax;

    @Override
    public TED buildToAvro() {
        return new TED(super.getId(), debitAccount, creditAccount, value, tax);
    }
}

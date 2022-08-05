package com.rayllanderson.kafka.consumer.payment.pix;

import com.rayllanderson.kafka.consumer.payment.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity(name = "tb_pix")
public class PIX implements Payment {
    @Id
    private String id;
    @Column(name = "debit_key")
    private String debitKey;
    @Column(name = "credit_key")
    private String creditKey;
    private Double value;

    public PIX(String debitKey, String creditKey, Double value) {
        this.id = UUID.randomUUID().toString();
        this.debitKey = debitKey;
        this.creditKey = creditKey;
        this.value = value;
    }

    public static Payment fromRecord(com.rayllanderson.kafka.payment.PIX record) {
        return new PIX(String.valueOf(record.getDebitKey()), String.valueOf(record.getCreditKey()), record.getValue());
    }
}

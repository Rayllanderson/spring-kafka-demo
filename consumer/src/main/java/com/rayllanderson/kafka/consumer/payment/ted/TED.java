package com.rayllanderson.kafka.consumer.payment.ted;

import com.rayllanderson.kafka.consumer.payment.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity(name = "tb_ted")
public class TED implements Payment {
    @Id
    private String id;
    @Column(name = "debit_account")
    private String debitAccount;
    @Column(name = "credit_account")
    private String creditAccount;
    private Double value;
    private Double tax;

    public TED(String debitAccount, String creditAccount, Double value, Double tax) {
        this.id = UUID.randomUUID().toString();
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.value = value;
        this.tax = tax;
    }

    public static Payment fromRecord(com.rayllanderson.kafka.payment.TED record) {
        return new TED(String.valueOf(record.getDebitAccount()), String.valueOf(record.getCreditAccount()), record.getValue(), record.getTax());
    }
}

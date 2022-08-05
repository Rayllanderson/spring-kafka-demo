package com.rayllanderson.kafka.consumer.payment;

import com.rayllanderson.kafka.consumer.payment.pix.PIX;
import com.rayllanderson.kafka.consumer.payment.pix.PIXRepository;
import com.rayllanderson.kafka.consumer.payment.ted.TED;
import com.rayllanderson.kafka.consumer.payment.ted.TEDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final TEDRepository tedRepository;
    private final PIXRepository pixRepository;

    @GetMapping("/pix")
    public ResponseEntity<List<PIX>> getAllPix() {
        return ResponseEntity.ok().body(pixRepository.findAll());
    }

    @GetMapping("/ted")
    public ResponseEntity<List<TED>> getAllTed() {
        return ResponseEntity.ok().body(tedRepository.findAll());
    }
}

package com.rayllanderson.kafka.producer.payment.pix;

import com.rayllanderson.kafka.producer.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/payments/pix")
public class PixController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Void> publish(@RequestBody PixRequest request) {
        paymentService.process(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

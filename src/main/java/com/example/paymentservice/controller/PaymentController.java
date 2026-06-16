package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentRequestDto;
import com.example.paymentservice.dto.PaymentResponseDto;
import com.example.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody PaymentRequestDto request) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }

    @PutMapping("/complete/{orderId}")
    public ResponseEntity<PaymentResponseDto> completePayment(@PathVariable Long orderId) {
        return ResponseEntity.ok(paymentService.completePayment(orderId));
    }

    @GetMapping("/courier/{courierId}")
    public ResponseEntity<List<PaymentResponseDto>> getCourierPayments(@PathVariable Long courierId) {
        return ResponseEntity.ok(paymentService.getCourierPayments(courierId));
    }
}
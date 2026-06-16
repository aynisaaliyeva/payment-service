package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentRequestDto;
import com.example.paymentservice.dto.PaymentResponseDto;
import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.enums.PaymentStatus;
import com.example.paymentservice.exception.PaymentNotFoundException;
import com.example.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentResponseDto createPayment(PaymentRequestDto request) {
        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .courierId(request.getCourierId())
                .amount(request.getAmount())
                .courierEarning(BigDecimal.ZERO)
                .courierBalance(BigDecimal.ZERO)
                .status(PaymentStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        Payment saved = paymentRepository.save(payment);
        return mapToResponse(saved);
    }

    public PaymentResponseDto completePayment(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new PaymentNotFoundException(orderId));

        BigDecimal earning = payment.getAmount()
                .multiply(BigDecimal.valueOf(0.8));

        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setCourierEarning(earning);
        payment.setCourierBalance(earning);
        payment.setCompletedAt(LocalDateTime.now());

        Payment saved = paymentRepository.save(payment);
        return mapToResponse(saved);
    }

    public List<PaymentResponseDto> getCourierPayments(Long courierId) {
        return paymentRepository.findByCourierId(courierId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private PaymentResponseDto mapToResponse(Payment payment) {
        return PaymentResponseDto.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .courierId(payment.getCourierId())
                .amount(payment.getAmount())
                .courierEarning(payment.getCourierEarning())
                .courierBalance(payment.getCourierBalance())
                .status(payment.getStatus())
                .createdAt(payment.getCreatedAt())
                .completedAt(payment.getCompletedAt())
                .build();
    }
}
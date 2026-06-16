package com.example.paymentservice.dto;

import com.example.paymentservice.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponseDto {
    private Long id;
    private Long orderId;
    private Long courierId;
    private BigDecimal amount;
    private BigDecimal courierEarning;
    private BigDecimal courierBalance;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
}
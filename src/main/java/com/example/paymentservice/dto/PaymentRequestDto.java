package com.example.paymentservice.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentRequestDto {
    private Long orderId;
    private Long courierId;
    private BigDecimal amount;
}
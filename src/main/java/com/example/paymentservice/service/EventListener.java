package com.example.paymentservice.service;

import com.example.paymentservice.config.RabbitMQConfig;
import com.example.paymentservice.dto.PaymentRequestDto;
import com.example.paymentservice.event.OrderCreatedEvent;
import com.example.paymentservice.event.OrderDeliveredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventListener {

    private final PaymentService paymentService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATED_QUEUE)
    public void onOrderCreated(OrderCreatedEvent event) {
        PaymentRequestDto request = new PaymentRequestDto();
        request.setOrderId(event.getOrderId());
        request.setCourierId(event.getCourierId());
        request.setAmount(event.getAmount());
        paymentService.createPayment(request);
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_DELIVERED_QUEUE)
    public void onOrderDelivered(OrderDeliveredEvent event) {
        paymentService.completePayment(event.getOrderId());
    }
}
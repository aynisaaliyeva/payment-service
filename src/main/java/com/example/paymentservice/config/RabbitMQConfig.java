package com.example.paymentservice.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ORDER_EXCHANGE = "order.exchange";

    public static final String ORDER_CREATED_QUEUE = "order.created.queue";
    public static final String ORDER_ASSIGNED_QUEUE = "order.assigned.queue";
    public static final String ORDER_DELIVERED_QUEUE = "order.delivered.queue";

    public static final String ORDER_CREATED_KEY = "order.created";
    public static final String ORDER_ASSIGNED_KEY = "order.assigned";
    public static final String ORDER_DELIVERED_KEY = "order.delivered";

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(ORDER_EXCHANGE);
    }

    @Bean
    public Queue orderCreatedQueue() {
        return new Queue(ORDER_CREATED_QUEUE);
    }

    @Bean
    public Queue orderAssignedQueue() {
        return new Queue(ORDER_ASSIGNED_QUEUE);
    }

    @Bean
    public Queue orderDeliveredQueue() {
        return new Queue(ORDER_DELIVERED_QUEUE);
    }

    @Bean
    public Binding orderCreatedBinding() {
        return BindingBuilder.bind(orderCreatedQueue())
                .to(orderExchange())
                .with(ORDER_CREATED_KEY);
    }

    @Bean
    public Binding orderAssignedBinding() {
        return BindingBuilder.bind(orderAssignedQueue())
                .to(orderExchange())
                .with(ORDER_ASSIGNED_KEY);
    }

    @Bean
    public Binding orderDeliveredBinding() {
        return BindingBuilder.bind(orderDeliveredQueue())
                .to(orderExchange())
                .with(ORDER_DELIVERED_KEY);
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}

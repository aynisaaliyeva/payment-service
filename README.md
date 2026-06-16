Payment Service

This service is responsible for managing courier earnings and payments.
When an order is created, a payment record is automatically created with PENDING status.
When an order is delivered, the payment is completed and the courier receives 80% of the delivery fee.
All financial operations are triggered by events received from RabbitMQ.

Technologies
Java 21, Spring Boot, PostgreSQL, RabbitMQ

Port: 8083

package com.example.customer.service;

import com.example.amqp.RabbitMQMessageProducer;
import com.example.clients.fraud.FraudClient;
import com.example.clients.fraud.dto.FraudCheckResponse;
import com.example.clients.notification.NotificationClient;
import com.example.clients.notification.dto.NotificationRequest;
import com.example.customer.dto.CustomerRegistrationRequest;
import com.example.customer.entity.Customer;
import com.example.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;

    @Transactional
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();

        // todo: check if email valid
        // todo: check if email not taken

        Customer savedCustomer = customerRepository.saveAndFlush(customer);

        // todo: check if fraudster

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(savedCustomer.getId());

        assert fraudCheckResponse != null;

        if (fraudCheckResponse.getIsFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        // todo: send notification
//        notificationClient.sendNotification(
//                new NotificationRequest(
//                        savedCustomer.getId(),
//                        savedCustomer.getEmail(),
//                        String.format("Hi %s, welcome to Microservices!", savedCustomer.getFirstName())
//                )
//        );

        NotificationRequest notificationRequest = new NotificationRequest(
                savedCustomer.getId(),
                savedCustomer.getEmail(),
                String.format("Hi %s, welcome to Microservices!", savedCustomer.getFirstName()));

        rabbitMQMessageProducer.publish(notificationRequest, this.internalExchange, this.internalNotificationRoutingKey);
    }
}

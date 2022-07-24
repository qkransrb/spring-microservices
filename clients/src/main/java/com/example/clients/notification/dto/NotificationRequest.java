package com.example.clients.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {

    private Long toCustomerId;
    private String toCustomerEmail;
    private String message;
}

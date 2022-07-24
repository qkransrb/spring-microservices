package com.example.notification.service;

import com.example.clients.notification.dto.NotificationRequest;
import com.example.notification.entity.Notification;
import com.example.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Transactional
    public void sendNotification(NotificationRequest request) {
        Notification notification = Notification.builder()
                .toCustomerId(request.getToCustomerId())
                .toCustomerEmail(request.getToCustomerEmail())
                .sender("qkransrb")
                .message(request.getMessage())
                .sentAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }
}

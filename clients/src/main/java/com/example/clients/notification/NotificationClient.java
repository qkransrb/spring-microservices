package com.example.clients.notification;

import com.example.clients.notification.dto.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("notification")
public interface NotificationClient {

    @PostMapping("/api/v1/notifications")
    void sendNotification(NotificationRequest notificationRequest);
}

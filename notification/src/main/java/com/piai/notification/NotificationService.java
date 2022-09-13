package com.piai.notification;

import com.piai.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;

    public void send(NotificationRequest request){
        repository.save(
                Notification.builder()
                        .toCustomerId(request.toCustomerId)
                        .toCustomerEmail(request.toCustomerName)
                        .sender("Piai")
                        .message(request.message)
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }
}

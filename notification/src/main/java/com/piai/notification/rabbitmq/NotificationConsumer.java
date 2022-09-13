package com.piai.notification.rabbitmq;

import com.piai.clients.notification.NotificationRequest;
import com.piai.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest request){
        log.info("Consumed {} from queue", request);
        notificationService.send(request);
    }
}

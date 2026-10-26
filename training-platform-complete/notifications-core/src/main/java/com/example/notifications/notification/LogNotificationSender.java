package com.example.notifications.notification;

import com.example.notifications.api.NotificationSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogNotificationSender implements NotificationSender {

    private static final Logger log =
            LoggerFactory.getLogger(LogNotificationSender.class);

    @Override
    public void send(String recipient, String message) {
        log.info("NOTIFICATION recipient={} message={}", recipient, message);
    }
}

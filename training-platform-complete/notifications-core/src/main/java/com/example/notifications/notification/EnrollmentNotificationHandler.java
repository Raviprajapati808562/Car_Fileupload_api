package com.example.notifications.notification;

import com.example.enrollments.api.EnrollmentCreatedEvent;
import com.example.notifications.api.NotificationSender;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentNotificationHandler {

    private final NotificationSender sender;

    public EnrollmentNotificationHandler(NotificationSender sender) {
        this.sender = sender;
    }

    @EventListener
    public void handle(EnrollmentCreatedEvent event) {
        sender.send(
                event.email(),
                "Enrollment confirmed for course " + event.courseId()
        );
    }
}

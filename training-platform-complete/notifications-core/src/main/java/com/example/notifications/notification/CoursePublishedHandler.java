package com.example.notifications.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.catalog.api.CoursePublishedEvent;

@Component
public class CoursePublishedHandler {

    private static final Logger log =
            LoggerFactory.getLogger(CoursePublishedHandler.class);

    @EventListener
    public void handle(CoursePublishedEvent event) {
        log.info("Enrollment domain aware: course published id={} title={}",
                event.courseId(), event.title());
    }
}

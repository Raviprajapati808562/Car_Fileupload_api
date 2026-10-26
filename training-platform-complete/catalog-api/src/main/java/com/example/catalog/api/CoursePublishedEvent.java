package com.example.catalog.api;

public record CoursePublishedEvent(
        Long courseId,
        String title
) {}

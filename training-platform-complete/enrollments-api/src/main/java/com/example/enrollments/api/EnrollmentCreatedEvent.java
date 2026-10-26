package com.example.enrollments.api;

public record EnrollmentCreatedEvent(
        Long enrollmentId,
        Long courseId,
        String name,
        String email
) {}

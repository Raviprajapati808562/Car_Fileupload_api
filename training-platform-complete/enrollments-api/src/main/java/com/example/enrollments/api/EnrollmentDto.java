package com.example.enrollments.api;

public record EnrollmentDto(
        Long id,
        String name,
        String email,
        Long courseId
) {}

package com.example.catalog.api;

public record CourseDto(
        Long id,
        String title,
        int capacity,
        String city,
        boolean published
) {}

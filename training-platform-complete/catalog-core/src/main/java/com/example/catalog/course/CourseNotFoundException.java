package com.example.catalog.course;

public class CourseNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public CourseNotFoundException(Long courseId) {
        super("Course not found: " + courseId);
    }
}

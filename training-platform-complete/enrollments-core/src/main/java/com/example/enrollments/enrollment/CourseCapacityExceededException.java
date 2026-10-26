package com.example.enrollments.enrollment;

public class CourseCapacityExceededException extends RuntimeException {
    public CourseCapacityExceededException(Long courseId) {
        super("Course is at capacity: " + courseId);
    }
}

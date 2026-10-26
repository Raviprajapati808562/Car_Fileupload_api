package com.example.enrollments.enrollment;

public class CourseNotPublishedException extends RuntimeException {
    public CourseNotPublishedException(Long courseId) {
        super("Course is not published: " + courseId);
    }
}

package com.example.enrollments.enrollment;

import com.example.catalog.api.CourseDto;
import com.example.catalog.api.CoursesApi;
import com.example.enrollments.api.EnrollmentCreatedEvent;
import com.example.enrollments.api.EnrollmentDto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository repository;
    private final CoursesApi coursesApi;
    private final ApplicationEventPublisher eventPublisher;

    public EnrollmentService(
            EnrollmentRepository repository,
            CoursesApi coursesApi,
            ApplicationEventPublisher eventPublisher
    ) {
        this.repository = repository;
        this.coursesApi = coursesApi;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public EnrollmentDto enroll(String name, String email, Long courseId) {
        CourseDto course = coursesApi.getCourse(courseId);

        if (!course.published()) {
            throw new CourseNotPublishedException(courseId);
        }

        long currentCount = repository.countByCourseId(courseId);
        if (currentCount >= course.capacity()) {
            throw new CourseCapacityExceededException(courseId);
        }

        Enrollment enrollment = repository.save(
                new Enrollment(name, email, courseId)
        );

        EnrollmentDto dto = toDto(enrollment);

        eventPublisher.publishEvent(
                new EnrollmentCreatedEvent(
                        dto.id(),
                        dto.courseId(),
                        dto.name(),
                        dto.email()
                )
        );

        return dto;
    }

    @Transactional(readOnly = true)
    public List<EnrollmentDto> findByCourse(Long courseId) {
        return repository.findByCourseId(courseId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private EnrollmentDto toDto(Enrollment enrollment) {
        return new EnrollmentDto(
                enrollment.getId(),
                enrollment.getName(),
                enrollment.getEmail(),
                enrollment.getCourseId()
        );
    }
}

package com.example.catalog.course;

import com.example.catalog.api.CourseDto;
import com.example.catalog.api.CoursePublishedEvent;
import com.example.catalog.api.CoursesApi;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService implements CoursesApi {

    private final CourseRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public CourseService(
            CourseRepository repository,
            ApplicationEventPublisher eventPublisher
    ) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public CourseDto create(String title, int capacity, String city) {
        Course course = repository.save(new Course(title, capacity, city));
        return toDto(course);
    }

    @Transactional
    public CourseDto publish(Long courseId) {
        Course course = repository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        course.publish();
        Course saved = repository.save(course);

        eventPublisher.publishEvent(
                new CoursePublishedEvent(saved.getId(), saved.getTitle())
        );

        return toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDto getCourse(Long courseId) {
        return repository.findById(courseId)
                .map(this::toDto)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
    }

    private CourseDto toDto(Course course) {
        return new CourseDto(
                course.getId(),
                course.getTitle(),
                course.getCapacity(),
                course.getCity(),
                course.isPublished()
        );
    }
}

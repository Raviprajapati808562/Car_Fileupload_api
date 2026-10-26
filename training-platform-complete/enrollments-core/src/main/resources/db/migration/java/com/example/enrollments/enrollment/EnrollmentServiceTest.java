package com.example.enrollments.enrollment;

import com.example.catalog.api.CourseDto;
import com.example.catalog.api.CoursesApi;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class EnrollmentServiceTest {

    @Test
    void rejectsEnrollmentWhenCourseIsAtCapacity() {
        EnrollmentRepository repository = Mockito.mock(EnrollmentRepository.class);
        CoursesApi coursesApi = Mockito.mock(CoursesApi.class);
        var publisher = Mockito.mock(
                org.springframework.context.ApplicationEventPublisher.class
        );

        when(coursesApi.getCourse(1L))
                .thenReturn(new CourseDto(1L, "Java", 2, "Gurugram", true));
        when(repository.countByCourseId(1L)).thenReturn(2L);

        EnrollmentService service =
                new EnrollmentService(repository, coursesApi, publisher);

        assertThrows(
                CourseCapacityExceededException.class,
                () -> service.enroll("Ravi", "ravi@example.com", 1L)
        );
    }
}

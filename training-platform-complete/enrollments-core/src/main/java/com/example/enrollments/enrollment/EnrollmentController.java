package com.example.enrollments.enrollment;

import com.example.enrollments.api.EnrollmentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnrollmentDto enroll(@RequestBody CreateEnrollmentRequest request) {
        return service.enroll(
                request.name(),
                request.email(),
                request.courseId()
        );
    }

    @GetMapping
    public List<EnrollmentDto> list(@RequestParam("courseId") Long courseId) {
        return service.findByCourse(courseId);
    }

    public record CreateEnrollmentRequest(
            String name,
            String email,
            Long courseId
    ) {}
}

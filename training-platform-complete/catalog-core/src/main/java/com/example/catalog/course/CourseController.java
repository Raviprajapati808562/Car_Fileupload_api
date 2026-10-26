package com.example.catalog.course;

import com.example.catalog.api.CourseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping("/saveData")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto create(@RequestBody CreateCourseRequest request) {
        return service.create(request.title(), request.capacity(), request.city());
    }

    @PostMapping("/{id}/publish")
    public CourseDto publish(@PathVariable("id") Long id) {
        return service.publish(id);
    }

    @GetMapping("/{id}")
    public CourseDto get(@PathVariable("id") Long id) {
        return service.getCourse(id);
    }

    public record CreateCourseRequest(
            String title,
            int capacity,
            String city
    ) {}
}

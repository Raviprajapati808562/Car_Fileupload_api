package com.example.enrollments.enrollment;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Long courseId;

    protected Enrollment() {
    }

    public Enrollment(String name, String email, Long courseId) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email is required");
        }
        if (courseId == null) {
            throw new IllegalArgumentException("courseId is required");
        }
        this.name = name;
        this.email = email;
        this.courseId = courseId;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Long getCourseId() { return courseId; }
}

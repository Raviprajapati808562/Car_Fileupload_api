package com.example.catalog.course;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private boolean published;

    protected Course() {
    }

    public Course(String title, int capacity, String city) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title is required");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be greater than zero");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("city is required");
        }
        this.title = title;
        this.capacity = capacity;
        this.city = city;
        this.published = false;
    }

    public void publish() {
        if (published) {
            return;
        }
        published = true;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public int getCapacity() { return capacity; }
    public String getCity() { return city; }
    public boolean isPublished() { return published; }
}

package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RatingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    private String rating;

    private LocalDateTime createdAt;

    // ✅ No-args constructor (required by JPA)
    public RatingLog() {
    }

    // ✅ All-args constructor
    public RatingLog(Property property, String rating, LocalDateTime createdAt) {
        this.property = property;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    // ✅ Getters
    public Long getId() {
        return id;
    }

    public Property getProperty() {
        return property;
    }

    public String getRating() {
        return rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // ✅ Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

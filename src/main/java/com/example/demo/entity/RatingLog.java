package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
public class RatingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    private Property property;

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Property getProperty() {
        return property;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public RatingLog(Property property, String rating, LocalDateTime createdAt) {
    this.property = property;
    this.rating = rating;
    this.createdAt = createdAt;
}

}

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "property_id", nullable = false, unique = true)
    private Property property;

    private Double finalRating;

    private String ratingCategory; // POOR / AVERAGE / GOOD / EXCELLENT

    private LocalDateTime ratedAt;

    @PrePersist
    public void onCreate() {
        this.ratedAt = LocalDateTime.now();
    }
}
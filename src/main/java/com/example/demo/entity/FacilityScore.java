package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long propertyId;
    private String facility;
    private double score;

    public FacilityScore() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPropertyId() { return propertyId; }
    public void setPropertyId(Long propertyId) { this.propertyId = propertyId; }

    public String getFacility() { return facility; }
    public void setFacility(String facility) { this.facility = facility; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
}

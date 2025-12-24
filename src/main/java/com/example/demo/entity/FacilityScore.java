package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double schoolProximity;
    private double hospitalProximity;
    private double transportAccess;
    private double safetyScore;

    @ManyToOne
    private Property property;

    public Long getId() {
        return id;
    }

    public double getSchoolProximity() {
        return schoolProximity;
    }

    public double getHospitalProximity() {
        return hospitalProximity;
    }

    public double getTransportAccess() {
        return transportAccess;
    }

    public double getSafetyScore() {
        return safetyScore;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}

package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "facility_scores")
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Property property;

    private Integer schoolProximity;
    private Integer hospitalProximity;
    private Integer transportAccess;
    private Integer safetyScore;

    public FacilityScore() {}

    public FacilityScore(Property property, Integer schoolProximity,
                         Integer hospitalProximity, Integer transportAccess,
                         Integer safetyScore) {
        this.property = property;
        this.schoolProximity = schoolProximity;
        this.hospitalProximity = hospitalProximity;
        this.transportAccess = transportAccess;
        this.safetyScore = safetyScore;
    }

    // ✅ REQUIRED GETTERS
    public Integer getSchoolProximity() { return schoolProximity; }
    public Integer getHospitalProximity() { return hospitalProximity; }
    public Integer getTransportAccess() { return transportAccess; }
    public Integer getSafetyScore() { return safetyScore; }

    public Property getProperty() { return property; }
}

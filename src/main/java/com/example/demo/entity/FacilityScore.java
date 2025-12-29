package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "facility_scores")
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "property_id", unique = true)
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

    // 🔥 REQUIRED SETTER
    public void setProperty(Property property) {
        this.property = property;
    }

    // getters
    public Property getProperty() { return property; }
    public Integer getSchoolProximity() { return schoolProximity; }
    public Integer getHospitalProximity() { return hospitalProximity; }
    public Integer getTransportAccess() { return transportAccess; }
    public Integer getSafetyScore() { return safetyScore; }
}

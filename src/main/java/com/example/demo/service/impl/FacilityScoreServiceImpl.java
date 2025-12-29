package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.FacilityScoreService;
import org.springframework.stereotype.Service;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

    private final FacilityScoreRepository repo;
    private final PropertyRepository propertyRepo;

    public FacilityScoreServiceImpl(FacilityScoreRepository repo,
                                    PropertyRepository propertyRepo) {
        this.repo = repo;
        this.propertyRepo = propertyRepo;
    }

    @Override
    public FacilityScore addScore(Long propertyId, FacilityScore score) {

        Property property = propertyRepo.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        // one score per property rule
        repo.findByProperty(property).ifPresent(s -> {
            throw new BadRequestException("Facility score already exists for property");
        });

        // attach property
        score = new FacilityScore(
                property,
                score.getSchoolProximity(),
                score.getHospitalProximity(),
                score.getTransportAccess(),
                score.getSafetyScore()
        );

        return repo.save(score);
    }

    @Override
    public FacilityScore getScoreByProperty(Long propertyId) {
        Property property = propertyRepo.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        return repo.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("Facility score not found"));
    }
}

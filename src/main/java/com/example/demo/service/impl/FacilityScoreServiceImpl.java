package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private FacilityScoreRepository facilityScoreRepository;

    @Override
    public FacilityScore addScore(Long propertyId, FacilityScore score) {

        Property property = propertyRepository.findById(propertyId).orElseThrow();

        if (facilityScoreRepository.findByProperty(property).isPresent()) {
            throw new RuntimeException("Score already exists");
        }

        score.setProperty(property);
        return facilityScoreRepository.save(score);
    }

    @Override
    public FacilityScore getScoreByProperty(Long propertyId) {

        Property property = propertyRepository.findById(propertyId).orElseThrow();
        return facilityScoreRepository.findByProperty(property).orElse(null);
    }
}

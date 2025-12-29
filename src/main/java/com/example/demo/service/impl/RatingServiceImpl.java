package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RatingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingResultRepository ratingResultRepository;
    private final FacilityScoreRepository facilityScoreRepository;
    private final PropertyRepository propertyRepository;

    public RatingServiceImpl(RatingResultRepository ratingResultRepository,
                             FacilityScoreRepository facilityScoreRepository,
                             PropertyRepository propertyRepository) {
        this.ratingResultRepository = ratingResultRepository;
        this.facilityScoreRepository = facilityScoreRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public RatingResult generateRating(Long propertyId) {

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        FacilityScore score = facilityScoreRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("Facility score not found"));

        // ✅ FIX HERE
        double finalRating =
                (score.getSchoolProximity()
                + score.getHospitalProximity()
                + score.getTransportAccess()
                + score.getSafetyScore()) / 4.0;

        String category;
        if (finalRating < 4) category = "POOR";
        else if (finalRating < 6) category = "AVERAGE";
        else if (finalRating < 8) category = "GOOD";
        else category = "EXCELLENT";

        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(finalRating);
        result.setRatingCategory(category);
        result.setRatedAt(LocalDateTime.now());

        return ratingResultRepository.save(result);
    }

    @Override
    public RatingResult getRating(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        return ratingResultRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found"));
    }
}

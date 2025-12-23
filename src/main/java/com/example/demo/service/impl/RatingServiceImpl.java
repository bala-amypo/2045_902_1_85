package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private final PropertyRepository propertyRepository;
    private final FacilityScoreRepository scoreRepository;
    private final RatingResultRepository ratingRepository;

    public RatingServiceImpl(PropertyRepository propertyRepository,
                             FacilityScoreRepository scoreRepository,
                             RatingResultRepository ratingRepository) {
        this.propertyRepository = propertyRepository;
        this.scoreRepository = scoreRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public RatingResult generateRating(Long propertyId) {

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new BadRequestException("Property not found"));

        FacilityScore score = scoreRepository.findByPropertyId(propertyId)
                .orElseThrow(() -> new BadRequestException("Facility score missing"));

        double avg = (score.getSchoolProximity()
                + score.getHospitalProximity()
                + score.getTransportAccess()
                + score.getSafetyScore()) / 4.0;

        String category;
        if (avg >= 8) category = "EXCELLENT";
        else if (avg >= 6) category = "GOOD";
        else if (avg >= 4) category = "AVERAGE";
        else category = "POOR";

        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(avg);
        result.setRatingCategory(category);

        return ratingRepository.save(result);
    }

    @Override
    public RatingResult getRating(Long propertyId) {
        return ratingRepository.findByPropertyId(propertyId)
                .orElseThrow(() -> new BadRequestException("Rating not found"));
    }
}
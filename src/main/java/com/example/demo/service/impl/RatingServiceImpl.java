package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.RatingResult;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.RatingResultRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl {

    private final FacilityScoreRepository scoreRepository;
    private final RatingResultRepository ratingRepository;

    public RatingServiceImpl(FacilityScoreRepository scoreRepository,
                             RatingResultRepository ratingRepository) {
        this.scoreRepository = scoreRepository;
        this.ratingRepository = ratingRepository;
    }

    public RatingResult calculateRating(Long propertyId) {

        FacilityScore score = scoreRepository.findByPropertyId(propertyId)
                .orElseThrow(() -> new RuntimeException("FacilityScore not found"));

        double finalRating =
                score.getSchoolProximity() +
                score.getHospitalProximity() +
                score.getTransportAccess() +
                score.getSafetyScore();

        RatingResult result = new RatingResult();
        result.setFinalRating(finalRating);
        result.setRatingCategory(finalRating > 15 ? "GOOD" : "AVERAGE");

        return ratingRepository.save(result);
    }
}

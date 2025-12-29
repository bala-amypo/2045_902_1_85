package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.repository.FacilityScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl {

    private final FacilityScoreRepository facilityScoreRepository;

    public RatingServiceImpl(FacilityScoreRepository facilityScoreRepository) {
        this.facilityScoreRepository = facilityScoreRepository;
    }

    public double calculateRating(Long propertyId) {

        List<FacilityScore> scores =
                facilityScoreRepository.findByPropertyId(propertyId);

        if (scores.isEmpty()) {
            throw new RuntimeException("No facility scores found");
        }

        return scores.stream()
                .mapToInt(FacilityScore::getScore)
                .average()
                .orElse(0.0);
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.service.FacilityScoreService;
import org.springframework.stereotype.Service;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

    private final FacilityScoreRepository scoreRepository;

    public FacilityScoreServiceImpl(FacilityScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    public FacilityScore getByPropertyId(Long propertyId) {
        return scoreRepository.findByPropertyId(propertyId)
                .orElseThrow(() -> new RuntimeException("FacilityScore not found"));
    }
}

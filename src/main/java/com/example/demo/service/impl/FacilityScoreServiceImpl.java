package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.repository.FacilityScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

    private final FacilityScoreRepository facilityScoreRepository;

    public FacilityScoreServiceImpl(FacilityScoreRepository facilityScoreRepository) {
        this.facilityScoreRepository = facilityScoreRepository;
    }

    @Override
    public FacilityScore addScore(Long propertyId, FacilityScore facilityScore) {
        facilityScore.setPropertyId(propertyId);
        return facilityScoreRepository.save(facilityScore);
    }

    @Override
    public List<FacilityScore> getScoreByProperty(Long propertyId) {
        return facilityScoreRepository.findByPropertyId(propertyId);
    }
}

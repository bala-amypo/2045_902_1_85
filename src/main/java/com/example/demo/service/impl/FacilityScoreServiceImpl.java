package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.service.FacilityScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

    private final FacilityScoreRepository repo;

    public FacilityScoreServiceImpl(FacilityScoreRepository repo) {
        this.repo = repo;
    }

    @Override
    public FacilityScore addScore(Long propertyId, FacilityScore score) {
        score.setPropertyId(propertyId);
        return repo.save(score);
    }

    @Override
    public List<FacilityScore> getScoreByProperty(Long propertyId) {
        return repo.findByPropertyId(propertyId);
    }
}

package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import java.util.List;

public interface FacilityScoreService {
    FacilityScore addScore(Long propertyId, FacilityScore score);
    List<FacilityScore> getScoreByProperty(Long propertyId);
}

package com.example.demo.repository;

import com.example.demo.entity.FacilityScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {

    List<FacilityScore> findByPropertyId(Long propertyId);
}

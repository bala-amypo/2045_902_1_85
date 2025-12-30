package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private FacilityScoreRepository facilityScoreRepository;

    @Autowired
    private RatingResultRepository ratingResultRepository;

    @Override
    public RatingResult generateRating(Long propertyId) {

        Property property = propertyRepository.findById(propertyId).orElseThrow();
        FacilityScore fs = facilityScoreRepository.findByProperty(property).orElseThrow();

        double avg = (fs.getSchoolProximity()
                + fs.getHospitalProximity()
                + fs.getTransportAccess()
                + fs.getSafetyScore()) / 4.0;

        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(avg);

        if (avg >= 8) result.setRatingCategory("EXCELLENT");
        else if (avg >= 6) result.setRatingCategory("GOOD");
        else if (avg >= 4) result.setRatingCategory("AVERAGE");
        else result.setRatingCategory("POOR");

        return ratingResultRepository.save(result);
    }

    @Override
    public RatingResult getRating(Long propertyId) {

        Property property = propertyRepository.findById(propertyId).orElseThrow();
        return ratingResultRepository.findByProperty(property).orElse(null);
    }
}

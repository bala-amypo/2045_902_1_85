package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingLogServiceImpl implements RatingLogService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private RatingLogRepository ratingLogRepository;

    @Override
    public RatingLog addLog(Long propertyId, String message) {

        Property property = propertyRepository.findById(propertyId).orElseThrow();

        RatingLog log = new RatingLog();
        log.setProperty(property);
        log.setMessage(message);

        return ratingLogRepository.save(log);
    }

    @Override
    public List<RatingLog> getLogsByProperty(Long propertyId) {

        Property property = propertyRepository.findById(propertyId).orElseThrow();
        return ratingLogRepository.findByProperty(property);
    }
}

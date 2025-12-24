package com.example.demo.service.impl;

import com.example.demo.entity.RatingLog;
import com.example.demo.repository.RatingLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingLogServiceImpl {

    private final RatingLogRepository logRepository;

    public RatingLogServiceImpl(RatingLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<RatingLog> getLogs(Long propertyId) {
        return logRepository.findByPropertyId(propertyId);
    }
}

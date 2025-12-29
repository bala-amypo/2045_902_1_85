package com.example.demo.repository;

import com.example.demo.entity.RatingLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.example.demo.entity.Property;


public interface RatingLogRepository extends JpaRepository<RatingLog, Long> {

    List<RatingLog> findByPropertyId(Long propertyId);
    List<RatingLog> findByProperty(Property property);

}

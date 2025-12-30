package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.service.FacilityScoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class FacilityScoreController {

    @Autowired
    private FacilityScoreService facilityScoreService;

    @PostMapping("/{propertyId}")
    public ResponseEntity<?> addScore(
            @PathVariable Long propertyId,
            @Valid @RequestBody FacilityScore score) {

        try {
            facilityScoreService.addScore(propertyId, score);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<FacilityScore> getScore(@PathVariable Long propertyId) {
        FacilityScore score = facilityScoreService.getScoreByProperty(propertyId);
        return ResponseEntity.ok(score);
    }
}

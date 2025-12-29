package com.example.demo.Controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.service.FacilityScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facility-score")
public class FacilityScoreController {

    private final FacilityScoreService facilityScoreService;

    public FacilityScoreController(FacilityScoreService facilityScoreService) {
        this.facilityScoreService = facilityScoreService;
    }

    @PostMapping("/{propertyId}")
    public FacilityScore addScore(
            @PathVariable Long propertyId,
            @RequestBody FacilityScore facilityScore) {

        return facilityScoreService.addScore(propertyId, facilityScore);
    }

    @GetMapping("/{propertyId}")
    public List<FacilityScore> getScores(@PathVariable Long propertyId) {
        return facilityScoreService.getScoreByProperty(propertyId);
    }
}

package com.example.demo.controller;

import com.example.demo.entity.RatingResult;
import com.example.demo.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/generate/{propertyId}")
    public ResponseEntity<RatingResult> generateRating(@PathVariable Long propertyId) {

        RatingResult result = ratingService.generateRating(propertyId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<RatingResult> getRating(@PathVariable Long propertyId) {

        RatingResult result = ratingService.getRating(propertyId);
        return ResponseEntity.ok(result);
    }
}

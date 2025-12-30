package com.example.demo.controller;

import com.example.demo.entity.RatingLog;
import com.example.demo.service.RatingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class RatingLogController {

    @Autowired
    private RatingLogService ratingLogService;

    @PostMapping("/{propertyId}")
    public ResponseEntity<RatingLog> addLog(
            @PathVariable Long propertyId,
            @RequestParam String message) {

        RatingLog log = ratingLogService.addLog(propertyId, message);
        return new ResponseEntity<>(log, HttpStatus.CREATED);
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<List<RatingLog>> getLogs(@PathVariable Long propertyId) {
        return ResponseEntity.ok(ratingLogService.getLogsByProperty(propertyId));
    }
}

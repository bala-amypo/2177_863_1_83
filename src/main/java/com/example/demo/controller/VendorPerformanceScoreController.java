package com.example.demo.controller;

import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.service.VendorPerformanceScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-performance-scores")
@Tag(name = "Vendor Performance Scores", description = "APIs for managing vendor performance scores")
public class VendorPerformanceScoreController {

    @Autowired
    private VendorPerformanceScoreService service;

    @PostMapping("/calculate/{vendorId}")
    @Operation(summary = "Calculate performance score for vendor")
    public ResponseEntity<VendorPerformanceScore> calculateScore(@PathVariable Long vendorId) {
        return ResponseEntity.ok(service.calculateScore(vendorId));
    }

    @GetMapping("/latest/{vendorId}")
    @Operation(summary = "Get latest performance score for vendor")
    public ResponseEntity<VendorPerformanceScore> getLatestScore(@PathVariable Long vendorId) {
        return ResponseEntity.ok(service.getLatestScore(vendorId));
    }

    @GetMapping("/history/{vendorId}")
    @Operation(summary = "Get performance score history for vendor")
    public ResponseEntity<List<VendorPerformanceScore>> getScoreHistory(@PathVariable Long vendorId) {
        return ResponseEntity.ok(service.getScoresForVendor(vendorId));
    }
}
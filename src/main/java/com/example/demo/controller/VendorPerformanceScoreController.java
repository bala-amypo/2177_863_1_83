package com.example.demo.controller;

import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.service.VendorPerformanceScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.List;

@RestController
@RequestMapping("/api/scores")
@Tag(name = "Vendor Performance Score API")
public class VendorPerformanceScoreController {

    private final VendorPerformanceScoreService service;

    public VendorPerformanceScoreController(VendorPerformanceScoreService service) {
        this.service = service;
    }

    @PostMapping("/calculate/{vendorId}")
    public ResponseEntity<VendorPerformanceScore> calculate(@PathVariable Long vendorId) {
        VendorPerformanceScore score = service.calculateScore(vendorId);
        if (score == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(score);
    }

    @GetMapping("/latest/{vendorId}")
    public ResponseEntity<VendorPerformanceScore> getLatest(@PathVariable Long vendorId) {
        VendorPerformanceScore score = service.getLatestScore(vendorId);
        if (score == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(score);
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<VendorPerformanceScore>> getHistory(@PathVariable Long vendorId) {
        List<VendorPerformanceScore> scores = service.getScoresForVendor(vendorId);
        return ResponseEntity.ok(scores);
    }
}

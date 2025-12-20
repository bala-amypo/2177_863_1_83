package com.example.demo.controller;

import com.example.demo.entity.VendorPerformanceScore;
import com.example.demo.service.VendorPerformanceScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
@Tag(name = "Vendor Performance Score API")
public class VendorPerformanceScoreController {

    private final VendorPerformanceScoreService service;

    public VendorPerformanceScoreController(VendorPerformanceScoreService service) {
        this.service = service;
    }

    // Returns all scores for the vendor (history)
    @GetMapping("/vendor/{vendorId}")
    public List<VendorPerformanceScore> getHistory(@PathVariable Long vendorId) {
        return service.getScoresForVendor(vendorId);
    }

    // Returns the latest score for the vendor
    @GetMapping("/latest/{vendorId}")
    public VendorPerformanceScore getLatest(@PathVariable Long vendorId) {
        return service.getLatestScore(vendorId);
    }

    // Returns numeric calculated score for the vendor
    @GetMapping("/calculate/{vendorId}")
    public double calculate(@PathVariable Long vendorId) {
        return service.calculateScore(vendorId);
    }
}

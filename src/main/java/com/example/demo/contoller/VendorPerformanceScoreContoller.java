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

    private final VendorPerformanceScoreService scoreService;

    public VendorPerformanceScoreController(VendorPerformanceScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/calculate/{vendorId}")
    public VendorPerformanceScore calculate(@PathVariable Long vendorId) {
        return scoreService.calculateScore(vendorId);
    }

    @GetMapping("/latest/{vendorId}")
    public VendorPerformanceScore getLatest(@PathVariable Long vendorId) {
        return scoreService.getLatestScore(vendorId);
    }

    @GetMapping("/vendor/{vendorId}")
    public List<VendorPerformanceScore> getHistory(@PathVariable Long vendorId) {
        return scoreService.getScoreHistory(vendorId);
    }
}

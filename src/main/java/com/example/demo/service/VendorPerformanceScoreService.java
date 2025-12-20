package com.example.demo.service;

import com.example.demo.entity.VendorPerformanceScore;
import java.util.List;

public interface VendorPerformanceScoreService {
    List<VendorPerformanceScore> getScoresForVendor(Long vendorId);
    VendorPerformanceScore getLatestScore(Long vendorId);

    // New method to calculate numeric performance score
    double calculateScore(Long vendorId);
}

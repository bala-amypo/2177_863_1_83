package com.example.demo.service;

import com.example.demo.model.VendorPerformanceScore;
import java.util.List;

public interface VendorPerformanceScoreService {
    VendorPerformanceScore calculatePerformanceScore(Long vendorId);  // Test #45, #46, #47
    VendorPerformanceScore calculateScore(Long vendorId);             // Test #77
    VendorPerformanceScore getLatestScore(Long vendorId);             // Test #78
    List<VendorPerformanceScore> getScoresForVendor(Long vendorId);   // Test #79
}

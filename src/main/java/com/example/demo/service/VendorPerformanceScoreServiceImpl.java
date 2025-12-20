package com.example.demo.service;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl {

    private final DeliveryEvaluationRepository evaluationRepository;
    private final VendorRepository vendorRepository;

    public VendorPerformanceScoreServiceImpl(DeliveryEvaluationRepository evaluationRepository,
                                             VendorRepository vendorRepository) {
        this.evaluationRepository = evaluationRepository;
        this.vendorRepository = vendorRepository;
    }

    public double calculatePerformanceScore(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        List<DeliveryEvaluation> evaluations = evaluationRepository.findByVendorId(vendorId);

        if (evaluations.isEmpty()) return 0.0;

        long onTime = evaluations.stream()
                .filter(e -> Boolean.TRUE.equals(e.getMeetsDeliveryTarget()))
                .count();

        long highQuality = evaluations.stream()
                .filter(e -> Boolean.TRUE.equals(e.getMeetsQualityTarget()))
                .count();

        // Count evaluations that meet both delivery and quality targets
        long bothTargets = evaluations.stream()
                .filter(e -> Boolean.TRUE.equals(e.getMeetsDeliveryTarget())
                          && Boolean.TRUE.equals(e.getMeetsQualityTarget()))
                .count();

        // Performance score as percentage
        return (bothTargets * 100.0) / evaluations.size();
    }
}

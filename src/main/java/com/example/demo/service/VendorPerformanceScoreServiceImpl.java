package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl
        implements VendorPerformanceScoreService {

    private final VendorPerformanceScoreRepository scoreRepo;
    private final DeliveryEvaluationRepository evalRepo;
    private final VendorRepository vendorRepo;
    private final VendorTierRepository tierRepo;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository scoreRepo,
            DeliveryEvaluationRepository evalRepo,
            VendorRepository vendorRepo,
            VendorTierRepository tierRepo) {

        this.scoreRepo = scoreRepo;
        this.evalRepo = evalRepo;
        this.vendorRepo = vendorRepo;
        this.tierRepo = tierRepo;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {

        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() ->
                        new RuntimeException("Vendor not found"));

        List<DeliveryEvaluation> evaluations =
                evalRepo.findByVendorId(vendorId);

        if (evaluations.isEmpty()) {
            throw new RuntimeException("No evaluations found");
        }

        double onTime = evaluations.stream()
                .filter(DeliveryEvaluation::getMeetsDeliveryTarget)
                .count() * 100.0 / evaluations.size();

        double quality = evaluations.stream()
                .filter(DeliveryEvaluation::getMeetsQualityTarget)
                .count() * 100.0 / evaluations.size();

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setOnTimePercentage(onTime);
        score.setQualityCompliancePercentage(quality);
        score.setOverallScore((onTime + quality) / 2);

        return scoreRepo.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        return scoreRepo.findByVendorIdOrderByCalculatedAtDesc(vendorId)
                .stream().findFirst().orElse(null);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepo.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}

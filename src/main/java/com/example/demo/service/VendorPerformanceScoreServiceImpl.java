package com.example.demo.service;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.entity.Vendor;
import com.example.demo.entity.VendorPerformanceScore;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {

    private final DeliveryEvaluationRepository evaluationRepository;
    private final VendorRepository vendorRepository;

    public VendorPerformanceScoreServiceImpl(DeliveryEvaluationRepository evaluationRepository,
                                             VendorRepository vendorRepository) {
        this.evaluationRepository = evaluationRepository;
        this.vendorRepository = vendorRepository;
    }

    // Returns all performance scores for a vendor
    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        List<DeliveryEvaluation> evaluations = evaluationRepository.findByVendorId(vendorId);

        return evaluations.stream()
                .map(e -> {
                    VendorPerformanceScore score = new VendorPerformanceScore();
                    score.setVendor(vendor);
                    score.setEvaluation(e);
                    score.setMeetsDeliveryTarget(Boolean.TRUE.equals(e.getMeetsDeliveryTarget()));
                    score.setMeetsQualityTarget(Boolean.TRUE.equals(e.getMeetsQualityTarget()));
                    return score;
                })
                .collect(Collectors.toList());
    }

    // Returns the latest performance score for a vendor
    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<DeliveryEvaluation> evaluations = evaluationRepository.findByVendorId(vendorId);

        if (evaluations.isEmpty()) return null;

        DeliveryEvaluation latest = evaluations.stream()
                .max((e1, e2) -> e1.getEvaluationDate().compareTo(e2.getEvaluationDate()))
                .orElse(null);

        if (latest == null) return null;

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(latest.getVendor());
        score.setEvaluation(latest);
        score.setMeetsDeliveryTarget(Boolean.TRUE.equals(latest.getMeetsDeliveryTarget()));
        score.setMeetsQualityTarget(Boolean.TRUE.equals(latest.getMeetsQualityTarget()));

        return score;
    }

    // Calculates numeric performance score (% of evaluations meeting both delivery and quality targets)
    @Override
    public double calculateScore(Long vendorId) {
        List<VendorPerformanceScore> scores = getScoresForVendor(vendorId);

        if (scores.isEmpty()) return 0.0;

        long count = scores.stream()
                .filter(s -> Boolean.TRUE.equals(s.getMeetsDeliveryTarget())
                          && Boolean.TRUE.equals(s.getMeetsQualityTarget()))
                .count();

        return (count * 100.0) / scores.size();
    }
}

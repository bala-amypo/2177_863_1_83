package com.example.demo.service;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.Vendor;
import com.example.demo.model.VendorPerformanceScore;
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

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        List<DeliveryEvaluation> evaluations = evaluationRepository.findByVendor_Id(vendorId); // updated

        DeliveryEvaluation latest = evaluations.stream()
                .max((e1, e2) -> e1.getEvaluationDate().compareTo(e2.getEvaluationDate()))
                .orElse(null);

        if (latest == null) return null;

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setEvaluation(latest);
        score.setMeetsDeliveryTarget(Boolean.TRUE.equals(latest.getMeetsDeliveryTarget()));
        score.setMeetsQualityTarget(Boolean.TRUE.equals(latest.getMeetsQualityTarget()));

        return score;
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        return calculateScore(vendorId);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        List<DeliveryEvaluation> evaluations = evaluationRepository.findByVendor_Id(vendorId); // updated

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
}

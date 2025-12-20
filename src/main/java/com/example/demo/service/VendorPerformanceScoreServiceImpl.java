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

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        List<DeliveryEvaluation> evaluations = evaluationRepository.findByVendorId(vendorId);

        return evaluations.stream()
                .map(e -> {
                    VendorPerformanceScore score = new VendorPerformanceScore();
                    score.setVendorId(vendor.getId());
                    score.setEvaluationId(e.getId());
                    score.setMeetsDeliveryTarget(Boolean.TRUE.equals(e.getMeetsDeliveryTarget()));
                    score.setMeetsQualityTarget(Boolean.TRUE.equals(e.getMeetsQualityTarget()));
                    return score;
                })
                .collect(Collectors.toList());
    }
}

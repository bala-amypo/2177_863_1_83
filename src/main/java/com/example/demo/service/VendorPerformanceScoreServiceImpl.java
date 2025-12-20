package com.example.demo.service;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.entity.Vendor;
import com.example.demo.entity.VendorPerformanceScore;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {

    private final DeliveryEvaluationRepository evaluationRepository;
    private final VendorRepository vendorRepository;
    private final VendorPerformanceScoreRepository scoreRepository;

    public VendorPerformanceScoreServiceImpl(DeliveryEvaluationRepository evaluationRepository,
                                             VendorRepository vendorRepository,
                                             VendorPerformanceScoreRepository scoreRepository) {
        this.evaluationRepository = evaluationRepository;
        this.vendorRepository = vendorRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        List<DeliveryEvaluation> evaluations = evaluationRepository.findByVendorId(vendorId);

        if (evaluations.isEmpty()) return null;

        DeliveryEvaluation latest = evaluations.stream()
                .max((e1, e2) -> e1.getEvaluationDate().compareTo(e2.getEvaluationDate()))
                .orElse(null);

        if (latest == null) return null;

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setEvaluation(latest);
        score.setMeetsDeliveryTarget(Boolean.TRUE.equals(latest.getMeetsDeliveryTarget()));
        score.setMeetsQualityTarget(Boolean.TRUE.equals(latest.getMeetsQualityTarget()));
        score.setScoreDate(latest.getEvaluationDate());

        return scoreRepository.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> scores = scoreRepository.findByVendorId(vendorId);
        return scores.stream()
                .max((s1, s2) -> s1.getScoreDate().compareTo(s2.getScoreDate()))
                .orElse(null);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepository.findByVendorId(vendorId);
    }
}

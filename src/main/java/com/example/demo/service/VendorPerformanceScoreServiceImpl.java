package com.example.demo.service;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.Vendor;
import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.repository.VendorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {

    private final DeliveryEvaluationRepository evaluationRepository;
    private final VendorRepository vendorRepository;
    private final VendorPerformanceScoreRepository scoreRepository;

    public VendorPerformanceScoreServiceImpl(
            DeliveryEvaluationRepository evaluationRepository,
            VendorRepository vendorRepository,
            VendorPerformanceScoreRepository scoreRepository) {
        this.evaluationRepository = evaluationRepository;
        this.vendorRepository = vendorRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    @Transactional
    public VendorPerformanceScore calculateScore(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        List<DeliveryEvaluation> evaluations = evaluationRepository.findByVendor_Id(vendorId);

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

        
        double calculatedScore = computeScore(latest);
        score.setScore(calculatedScore);

        return scoreRepository.save(score);
    }

    @Override
    @Transactional
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        return calculateScore(vendorId);
    }

    @Override
    @Transactional
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        List<DeliveryEvaluation> evaluations = evaluationRepository.findByVendor_Id(vendorId);

        return evaluations.stream()
                .map(e -> {
                    VendorPerformanceScore score = new VendorPerformanceScore();
                    score.setVendor(vendor);
                    score.setEvaluation(e);
                    score.setMeetsDeliveryTarget(Boolean.TRUE.equals(e.getMeetsDeliveryTarget()));
                    score.setMeetsQualityTarget(Boolean.TRUE.equals(e.getMeetsQualityTarget()));
                    score.setScoreDate(e.getEvaluationDate());

                    
                    score.setScore(computeScore(e));

                    return score;
                })
                .collect(Collectors.toList());
    }

    private double computeScore(DeliveryEvaluation evaluation) {
        double score = 0.0;

        if (evaluation.getMeetsDeliveryTarget() != null && evaluation.getMeetsDeliveryTarget()) {
            score += 50; 
        }
        if (evaluation.getMeetsQualityTarget() != null && evaluation.getMeetsQualityTarget()) {
            score += 50; 
        }

        
        return score;
    }
}

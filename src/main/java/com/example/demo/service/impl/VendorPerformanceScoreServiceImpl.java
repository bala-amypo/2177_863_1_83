package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.Vendor;
import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorPerformanceScoreService;

@Service
public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {
    private final VendorPerformanceScoreRepository vendorPerformanceScoreRepository;
    private final DeliveryEvaluationRepository deliveryEvaluationRepository;
    private final VendorRepository vendorRepository;
    private final VendorTierRepository vendorTierRepository;
    
    public VendorPerformanceScoreServiceImpl(VendorPerformanceScoreRepository vendorPerformanceScoreRepository,
                                           DeliveryEvaluationRepository deliveryEvaluationRepository,
                                           VendorRepository vendorRepository,
                                           VendorTierRepository vendorTierRepository) {
        this.vendorPerformanceScoreRepository = vendorPerformanceScoreRepository;
        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
        this.vendorRepository = vendorRepository;
        this.vendorTierRepository = vendorTierRepository;
    }
    
    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
            .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
        
        List<DeliveryEvaluation> evaluations = deliveryEvaluationRepository.findByVendorId(vendorId);
        
        if (evaluations.isEmpty()) {
            return vendorPerformanceScoreRepository.save(new VendorPerformanceScore(vendor, 0.0, 0.0, 0.0));
        }
        
        long onTimeCount = evaluations.stream().mapToLong(e -> Boolean.TRUE.equals(e.getMeetsDeliveryTarget()) ? 1 : 0).sum();
        long qualityCount = evaluations.stream().mapToLong(e -> Boolean.TRUE.equals(e.getMeetsQualityTarget()) ? 1 : 0).sum();
        
        double onTimePercentage = (double) onTimeCount / evaluations.size() * 100;
        double qualityPercentage = (double) qualityCount / evaluations.size() * 100;
        double overallScore = (onTimePercentage + qualityPercentage) / 2;
        
        VendorPerformanceScore score = new VendorPerformanceScore(vendor, onTimePercentage, qualityPercentage, overallScore);
        return vendorPerformanceScoreRepository.save(score);
    }
    
    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> scores = vendorPerformanceScoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId);
        return scores.isEmpty() ? null : scores.get(0);
    }
    
    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return vendorPerformanceScoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}
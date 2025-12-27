package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {
    private final DeliveryEvaluationRepository deliveryEvaluationRepository;
    private final VendorRepository vendorRepository;
    private final SLARequirementRepository slaRequirementRepository;

    @Autowired
    public DeliveryEvaluationServiceImpl(DeliveryEvaluationRepository deliveryEvaluationRepository,
                                       VendorRepository vendorRepository,
                                       SLARequirementRepository slaRequirementRepository) {
        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
        this.vendorRepository = vendorRepository;
        this.slaRequirementRepository = slaRequirementRepository;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {
        validateEvaluation(evaluation);
        
        // Set compliance flags
        evaluation.setMeetsDeliveryTarget(evaluation.getActualDeliveryDays() <= 
            evaluation.getSlaRequirement().getMaxDeliveryDays());
        evaluation.setMeetsQualityTarget(evaluation.getQualityScore() >= 
            evaluation.getSlaRequirement().getMinQualityScore());
            
        return deliveryEvaluationRepository.save(evaluation);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return deliveryEvaluationRepository.findByVendorId(vendorId);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long slaId) {
        return deliveryEvaluationRepository.findBySlaRequirementId(slaId);
    }

    @Override
    public List<DeliveryEvaluation> findHighQualityDeliveries(Vendor vendor, Double threshold) {
        return deliveryEvaluationRepository.findHighQualityDeliveries(vendor, threshold);
    }

    @Override
    public List<DeliveryEvaluation> findOnTimeDeliveries(SLARequirement sla) {
        return deliveryEvaluationRepository.findOnTimeDeliveries(sla);
    }

    private void validateEvaluation(DeliveryEvaluation eval) {
        // Vendor validation - test #36
        if (eval.getVendor().getId() != null) {
            Vendor vendor = vendorRepository.findById(eval.getVendor().getId()).orElse(null);
            if (vendor != null && !vendor.getActive()) {
                throw new IllegalStateException("Only active vendors can have evaluations");
            }
        }
        
        // SLA validation
        if (eval.getSlaRequirement().getId() != null) {
            SLARequirement sla = slaRequirementRepository.findById(eval.getSlaRequirement().getId()).orElse(null);
            if (sla != null && !sla.getActive()) {
                throw new IllegalStateException("Only active SLA requirements can be used");
            }
        }
        
        // Delivery days validation - test #37
        if (eval.getActualDeliveryDays() == null || eval.getActualDeliveryDays() < 0) {
            throw new IllegalArgumentException("Delivery days must be >= 0");
        }
        
        // Quality score validation - test #38
        if (eval.getQualityScore() == null || eval.getQualityScore() < 0 || eval.getQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100");
        }
    }
}

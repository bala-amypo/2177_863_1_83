package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.Vendor;
import com.example.demo.model.SLARequirement;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository deliveryEvaluationRepository;
    private final VendorRepository vendorRepository;
    private final SLARequirementRepository slaRequirementRepository;

    public DeliveryEvaluationServiceImpl(
            DeliveryEvaluationRepository deliveryEvaluationRepository,
            VendorRepository vendorRepository,
            SLARequirementRepository slaRequirementRepository) {

        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
        this.vendorRepository = vendorRepository;
        this.slaRequirementRepository = slaRequirementRepository;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {

        Vendor vendor = vendorRepository.findById(evaluation.getVendor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        SLARequirement sla = slaRequirementRepository.findById(evaluation.getSlaRequirement().getId())
                .orElseThrow(() -> new IllegalArgumentException("SLA requirement not found"));

        if (!vendor.getActive()) {
            throw new IllegalStateException("Vendor is not active");
        }

        evaluation.setMeetsDeliveryTarget(
                evaluation.getActualDeliveryDays() <= sla.getMaxDeliveryDays()
        );

        evaluation.setMeetsQualityTarget(
                evaluation.getQualityScore() >= sla.getMinQualityScore()
        );

        return deliveryEvaluationRepository.save(evaluation);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return deliveryEvaluationRepository.findByVendorId(vendorId);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId) {
        return deliveryEvaluationRepository.findBySlaRequirementId(requirementId);
    }

    // ✅ THIS WAS MISSING – NOW FIXED
    @Override
    public List<DeliveryEvaluation> findOnTimeDeliveries(SLARequirement requirement) {
        return deliveryEvaluationRepository
                .findBySlaRequirementIdAndMeetsDeliveryTargetTrue(requirement.getId());
    }
}

package com.example.demo.service;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.entity.SLARequirement;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository evaluationRepository;
    private final VendorRepository vendorRepository;
    private final SLARequirementRepository slaRepository;

    public DeliveryEvaluationServiceImpl(DeliveryEvaluationRepository evaluationRepository,
                                         VendorRepository vendorRepository,
                                         SLARequirementRepository slaRepository) {
        this.evaluationRepository = evaluationRepository;
        this.vendorRepository = vendorRepository;
        this.slaRepository = slaRepository;
    }

    @Override
    public DeliveryEvaluation createEvaluation(Long vendorId,
                                               Long slaRequirementId,
                                               Integer actualDeliveryDays,
                                               Double qualityScore,
                                               LocalDate evaluationDate) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found with id: " + vendorId));
        if (!vendor.getActive()) {
            throw new IllegalStateException("Vendor is not active");
        }

        SLARequirement sla = slaRepository.findById(slaRequirementId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "SLA Requirement not found with id: " + slaRequirementId));
        if (!sla.getActive()) {
            throw new IllegalStateException("SLA Requirement is not active");
        }

        if (actualDeliveryDays == null || actualDeliveryDays < 0) {
            throw new IllegalArgumentException("Actual delivery days must be >= 0");
        }
        if (qualityScore == null || qualityScore < 0 || qualityScore > 100) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100");
        }

        if (evaluationDate == null) {
            evaluationDate = LocalDate.now();
        }

        DeliveryEvaluation evaluation = new DeliveryEvaluation();
        evaluation.setVendor(vendor);
        evaluation.setSlaRequirement(sla);
        evaluation.setActualDeliveryDays(actualDeliveryDays);
        evaluation.setQualityScore(qualityScore);
        evaluation.setEvaluationDate(evaluationDate);

        evaluation.setMeetsDeliveryTarget(actualDeliveryDays <= sla.getMaxDeliveryDays());
        evaluation.setMeetsQualityTarget(qualityScore >= sla.getMinQualityScore());

        return evaluationRepository.save(evaluation);
    }

    @Override
    public DeliveryEvaluation getEvaluationById(Long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evaluation not found with id: " + id));
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return evaluationRepository.findByVendor_Id(vendorId);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId) {
        return evaluationRepository.findBySlaRequirement_Id(requirementId);
    }
}

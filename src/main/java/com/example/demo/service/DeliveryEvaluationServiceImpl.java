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

        // Fetch Vendor
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found with id: " + vendorId));
        if (!vendor.getActive()) throw new IllegalStateException("Vendor is not active");

        // Fetch SLA Requirement
        SLARequirement sla = slaRepository.findById(slaRequirementId)
                .orElseThrow(() -> new IllegalArgumentException("SLA Requirement not found with id: " + slaRequirementId));
        if (!sla.getActive()) throw new IllegalStateException("SLA Requirement is not active");

        // Validate inputs
        if (actualDeliveryDays == null || actualDeliveryDays < 0)
            throw new IllegalArgumentException("Actual delivery days must be >= 0");
        if (qualityScore == null || qualityScore < 0 || qualityScore > 100)
            throw new IllegalArgumentException("Quality score must be between 0 and 100");

        // Set evaluation date if null
        if (evaluationDate == null) evaluationDate = LocalDate.now();

        // Build DeliveryEvaluation
        DeliveryEvaluation evaluation = new DeliveryEvaluation();
        evaluation.setVendor(vendor);
        evaluation.setSlaRequirement(sla);
        evaluation.setActualDeliveryDays(actualDeliveryDays);
        evaluation.setQualityScore(qualityScore);
        evaluation.setEvaluationDate(evaluationDate);

        // Compute targets
        evaluation.setMeetsDeliveryTarget(actualDeliveryDays <= sla.getMaxDeliveryDays());
        evaluation.setMeetsQualityTarget(qualityScore >= sla.getMinQualityScore());

        // Save and return
        return evaluationRepository.save(evaluation);
    }

    @Override
    public DeliveryEvaluation getEvaluationById(Long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evaluation not found with id: " + id));
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return evaluationRepository.findByVendorId(vendorId);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId) {
        return evaluationRepository.findBySlaRequirementId(requirementId);
    }
}

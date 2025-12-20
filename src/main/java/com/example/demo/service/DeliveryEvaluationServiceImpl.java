package com.example.demo.service;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.SLARequirement;
import com.example.demo.model.Vendor;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository evaluationRepo;
    private final VendorRepository vendorRepo;
    private final SLARequirementRepository slaRepo;

    public DeliveryEvaluationServiceImpl(
            DeliveryEvaluationRepository evaluationRepo,
            VendorRepository vendorRepo,
            SLARequirementRepository slaRepo) {
        this.evaluationRepo = evaluationRepo;
        this.vendorRepo = vendorRepo;
        this.slaRepo = slaRepo;
    }

    @Override
    public DeliveryEvaluation createEvaluation(
            Long vendorId,
            Long slaRequirementId,
            Integer actualDeliveryDays,
            Double qualityScore,
            LocalDate evaluationDate) {

        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        SLARequirement sla = slaRepo.findById(slaRequirementId)
                .orElseThrow(() -> new RuntimeException("SLA Requirement not found"));

        DeliveryEvaluation evaluation = new DeliveryEvaluation();
        evaluation.setVendor(vendor);
        evaluation.setSlaRequirement(sla);
        evaluation.setActualDeliveryDays(actualDeliveryDays);
        evaluation.setQualityScore(qualityScore);
        evaluation.setEvaluationDate(evaluationDate);

        evaluation.setMeetsDeliveryTarget(
                actualDeliveryDays <= sla.getMaxDeliveryDays()
        );

        evaluation.setMeetsQualityTarget(
                qualityScore >= sla.getMinQualityScore()
        );

        return evaluationRepo.save(evaluation);
    }

    @Override
    public DeliveryEvaluation getEvaluationById(Long id) {
        return evaluationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
    }

@Override
public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
    return evaluationRepo.findByVendor_Id(vendorId);
}

@Override
public List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId) {
    return evaluationRepo.findBySlaRequirement_Id(requirementId);
}

}

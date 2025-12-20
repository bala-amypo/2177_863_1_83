package com.example.demo.service;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.SLARequirement;
import com.example.demo.model.Vendor;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.repository.VendorRepository;
import org.springframework.stereotype.Service;

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
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {

        // 1️⃣ Fetch Vendor from DB
        Vendor vendor = vendorRepo.findById(
                evaluation.getVendor().getId()
        ).orElseThrow(() ->
                new RuntimeException("Vendor not found")
        );

        // 2️⃣ Fetch SLARequirement from DB
        SLARequirement sla = slaRepo.findById(
                evaluation.getSlaRequirement().getId()
        ).orElseThrow(() ->
                new RuntimeException("SLA Requirement not found")
        );

        // 3️⃣ Attach managed entities
        evaluation.setVendor(vendor);
        evaluation.setSlaRequirement(sla);

        // 4️⃣ Business logic
        evaluation.setMeetsDeliveryTarget(
                evaluation.getActualDeliveryDays() <= sla.getMaxDeliveryDays()
        );

        evaluation.setMeetsQualityTarget(
                evaluation.getQualityScore() >= sla.getMinQualityScore()
        );

        // 5️⃣ Save
        return evaluationRepo.save(evaluation);
    }
}

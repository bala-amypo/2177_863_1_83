package com.example.demo.service;

import com.example.demo.model.DeliveryEvaluation;

import java.time.LocalDate;
import java.util.List;

public interface DeliveryEvaluationService {

    DeliveryEvaluation createEvaluation(
            Long vendorId,
            Long slaRequirementId,
            int actualDeliveryDays,
            Double qualityScore,
            LocalDate evaluationDate
    );

    DeliveryEvaluation getEvaluationById(Long id);

    List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId);

    List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId);
}

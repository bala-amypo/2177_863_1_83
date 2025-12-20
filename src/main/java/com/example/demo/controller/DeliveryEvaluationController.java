package com.example.demo.controller;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.service.DeliveryEvaluationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@Tag(name = "Delivery Evaluation API")
public class DeliveryEvaluationController {

    private final DeliveryEvaluationService service;

    public DeliveryEvaluationController(DeliveryEvaluationService service) {
        this.service = service;
    }

    // Updated POST method
    @PostMapping
    public DeliveryEvaluation create(@RequestBody EvaluationRequest request) {
        return service.createEvaluation(
                request.getVendorId(),
                request.getSlaRequirementId(),
                request.getActualDeliveryDays(),
                request.getQualityScore(),
                request.getEvaluationDate()
        );
    }

    @GetMapping("/{id}")
    public DeliveryEvaluation getById(@PathVariable Long id) {
        return service.getEvaluationById(id);
    }

    @GetMapping("/vendor/{vendorId}")
    public List<DeliveryEvaluation> getByVendor(@PathVariable Long vendorId) {
        return service.getEvaluationsForVendor(vendorId);
    }

    @GetMapping("/requirement/{requirementId}")
    public List<DeliveryEvaluation> getByRequirement(@PathVariable Long requirementId) {
        return service.getEvaluationsForRequirement(requirementId);
    }

    // DTO class to map incoming JSON
    public static class EvaluationRequest {
        private Long vendorId;
        private Long slaRequirementId;
        private Integer actualDeliveryDays;
        private Double qualityScore;
        private LocalDate evaluationDate;

        // Getters and Setters
        public Long getVendorId() { return vendorId; }
        public void setVendorId(Long vendorId) { this.vendorId = vendorId; }

        public Long getSlaRequirementId() { return slaRequirementId; }
        public void setSlaRequirementId(Long slaRequirementId) { this.slaRequirementId = slaRequirementId; }

        public Integer getActualDeliveryDays() { return actualDeliveryDays; }
        public void setActualDeliveryDays(Integer actualDeliveryDays) { this.actualDeliveryDays = actualDeliveryDays; }

        public Double getQualityScore() { return qualityScore; }
        public void setQualityScore(Double qualityScore) { this.qualityScore = qualityScore; }

        public LocalDate getEvaluationDate() { return evaluationDate; }
        public void setEvaluationDate(LocalDate evaluationDate) { this.evaluationDate = evaluationDate; }
    }
}

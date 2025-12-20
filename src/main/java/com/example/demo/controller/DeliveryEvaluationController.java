package com.example.demo.controller;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.service.DeliveryEvaluationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@Tag(name = "Delivery Evaluation API")
public class DeliveryEvaluationController {

    private final DeliveryEvaluationService service;

    public DeliveryEvaluationController(DeliveryEvaluationService service) {
        this.service = service;
    }

    @PostMapping
    public DeliveryEvaluation create(@RequestBody DeliveryEvaluation evaluation) {

        // ✅ Read IDs from nested entities (NO DTO)
        return service.createEvaluation(
                evaluation.getVendor().getId(),
                evaluation.getSlaRequirement().getId(),
                evaluation.getActualDeliveryDays(),
                evaluation.getQualityScore(),
                evaluation.getEvaluationDate()
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
}

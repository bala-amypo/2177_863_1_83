package com.example.demo.controller;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class DeliveryEvaluationController {

    private final DeliveryEvaluationService service;

    public DeliveryEvaluationController(DeliveryEvaluationService service) {
        this.service = service;
    }

    // ✅ FIXED: accept DeliveryEvaluation object
    @PostMapping
    public DeliveryEvaluation create(@RequestBody DeliveryEvaluation evaluation) {
        return service.createEvaluation(evaluation);
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
    public List<DeliveryEvaluation> getByRequirement(
            @PathVariable Long requirementId) {
        return service.getEvaluationsForRequirement(requirementId);
    }
}

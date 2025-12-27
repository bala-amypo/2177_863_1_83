package com.example.demo.controller;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@SecurityRequirement(name="bearerAuth")
public class DeliveryEvaluationController {

    private final DeliveryEvaluationService deliveryEvaluationService;

    public DeliveryEvaluationController(
            DeliveryEvaluationService deliveryEvaluationService) {
        this.deliveryEvaluationService = deliveryEvaluationService;
    }

    @PostMapping
    public ResponseEntity<DeliveryEvaluation> create(
            @RequestBody DeliveryEvaluation evaluation) {
        return ResponseEntity.ok(
                deliveryEvaluationService.createEvaluation(evaluation)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryEvaluation> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                deliveryEvaluationService.getEvaluationById(id)
        );
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<DeliveryEvaluation>>
    getByVendor(@PathVariable Long vendorId) {
        return ResponseEntity.ok(
                deliveryEvaluationService.getEvaluationsForVendor(vendorId)
        );
    }

    @GetMapping("/requirement/{reqId}")
    public ResponseEntity<List<DeliveryEvaluation>>
    getByRequirement(@PathVariable Long reqId) {
        return ResponseEntity.ok(
                deliveryEvaluationService.getEvaluationsForRequirement(reqId)
        );
    }
}

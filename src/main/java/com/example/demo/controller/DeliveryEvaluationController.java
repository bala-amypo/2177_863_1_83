package com.example.demo.controller;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.service.DeliveryEvaluationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-evaluations")
@Tag(name = "Delivery Evaluations", description = "APIs for managing delivery evaluations")
public class DeliveryEvaluationController {

    @Autowired
    private DeliveryEvaluationService service;

    @PostMapping
    @Operation(summary = "Create delivery evaluation")
    public ResponseEntity<DeliveryEvaluation> createEvaluation(@RequestBody DeliveryEvaluation evaluation) {
        return ResponseEntity.ok(service.createEvaluation(evaluation));
    }

    @GetMapping("/vendor/{vendorId}")
    @Operation(summary = "Get evaluations by vendor")
    public ResponseEntity<List<DeliveryEvaluation>> getByVendor(@PathVariable Long vendorId) {
        return ResponseEntity.ok(service.getEvaluationsForVendor(vendorId));
    }

    @GetMapping("/requirement/{requirementId}")
    @Operation(summary = "Get evaluations by requirement")
    public ResponseEntity<List<DeliveryEvaluation>> getByRequirement(@PathVariable Long requirementId) {
        return ResponseEntity.ok(service.getEvaluationsForRequirement(requirementId));
    }
}
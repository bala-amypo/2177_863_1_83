package com.example.demo.controller;

import com.example.demo.model.SLARequirement;
import com.example.demo.service.SLARequirementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sla-requirements")
@Tag(name = "SLA Requirements", description = "APIs for managing SLA requirements")
public class SLARequirementController {

    @Autowired
    private SLARequirementService slaRequirementService;

    @GetMapping
    @Operation(summary = "Get all SLA requirements")
    public ResponseEntity<List<SLARequirement>> getAllRequirements() {
        return ResponseEntity.ok(slaRequirementService.getAllRequirements());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get SLA requirement by ID")
    public ResponseEntity<SLARequirement> getRequirementById(@PathVariable Long id) {
        return ResponseEntity.ok(slaRequirementService.getRequirementById(id));
    }

    @PostMapping
    @Operation(summary = "Create SLA requirement")
    public ResponseEntity<SLARequirement> createRequirement(@RequestBody SLARequirement requirement) {
        return ResponseEntity.ok(slaRequirementService.createRequirement(requirement));
    }
}
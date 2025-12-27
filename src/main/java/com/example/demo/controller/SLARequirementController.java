package com.example.demo.controller;

import com.example.demo.model.SLARequirement;
import com.example.demo.service.SLARequirementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/sla-requirements")
@SecurityRequirement(name="bearerAuth")
public class SLARequirementController {

    private final SLARequirementService slaRequirementService;

    public SLARequirementController(SLARequirementService slaRequirementService) {
        this.slaRequirementService = slaRequirementService;
    }

    @PostMapping
    public ResponseEntity<SLARequirement> create(@RequestBody SLARequirement req) {
        return ResponseEntity.ok(
                slaRequirementService.createRequirement(req)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SLARequirement> update(@PathVariable Long id,
                                                 @RequestBody SLARequirement req) {
        return ResponseEntity.ok(
                slaRequirementService.updateRequirement(id, req)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SLARequirement> get(@PathVariable Long id) {
        return ResponseEntity.ok(
                slaRequirementService.getRequirementById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<SLARequirement>> getAll() {
        return ResponseEntity.ok(
                slaRequirementService.getAllRequirements()
        );
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        slaRequirementService.deactivateRequirement(id);
        return ResponseEntity.ok().build();
    }
}

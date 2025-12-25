package com.example.demo.controller;

import com.example.demo.model.SLARequirement;
import com.example.demo.service.SLARequirementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/sla-requirements")
@Tag(name = "SLA Requirement API")
public class SLARequirementController {

    private final SLARequirementService service;

    public SLARequirementController(SLARequirementService service) {
        this.service = service;
    }

    @PostMapping
    public SLARequirement create(@RequestBody SLARequirement sla) {
        return service.createRequirement(sla);
    }

    @PutMapping("/{id}")
    public SLARequirement update(@PathVariable Long id,
                                 @RequestBody SLARequirement sla) {
        return service.updateRequirement(id, sla);
    }

    @GetMapping("/{id}")
    public SLARequirement getById(@PathVariable Long id) {
        return service.getRequirementById(id);
    }

    @GetMapping
    public List<SLARequirement> getAll() {
        return service.getAllRequirements();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateRequirement(id);
    }
}

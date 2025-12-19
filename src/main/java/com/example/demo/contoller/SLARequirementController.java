package com.example.demo.controller;

import com.example.demo.entity.SLARequirement;
import com.example.demo.service.SLARequirementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sla-requirements")
@Tag(name = "SLA Requirement API")
public class SLARequirementController {

    private final SLARequirementService slaService;

    public SLARequirementController(SLARequirementService slaService) {
        this.slaService = slaService;
    }

    @PostMapping
    public SLARequirement create(@RequestBody SLARequirement sla) {
        return slaService.create(sla);
    }

    @PutMapping("/{id}")
    public SLARequirement update(@PathVariable Long id, @RequestBody SLARequirement sla) {
        return slaService.update(id, sla);
    }

    @GetMapping("/{id}")
    public SLARequirement getById(@PathVariable Long id) {
        return slaService.getById(id);
    }

    @GetMapping
    public List<SLARequirement> getAll() {
        return slaService.getAll();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        slaService.deactivate(id);
    }
}

package com.example.demo.controller;

import com.example.demo.service.SLARequirementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sla")
public class SLARequirementController {

    private final SLARequirementService slaRequirementService;

    public SLARequirementController(SLARequirementService slaRequirementService) {
        this.slaRequirementService = slaRequirementService;
    }

    @PostMapping("/create")
    public String createSLA() {
        slaRequirementService.createSLA();
        return "SLA created successfully";
    }
}

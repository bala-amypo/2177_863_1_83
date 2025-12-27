package com.example.demo.service;

import com.example.demo.model.SLARequirement;

public interface SLARequirementService {
    SLARequirement createRequirement(SLARequirement requirement);
    SLARequirement updateRequirement(Long id, SLARequirement requirement);
    void deactivateRequirement(Long id);
}

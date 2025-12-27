package com.example.demo.service.impl;

import com.example.demo.model.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;
import org.springframework.stereotype.Service;

@Service
public class SLARequirementServiceImpl implements SLARequirementService {
    private final SLARequirementRepository slaRequirementRepository;

    public SLARequirementServiceImpl(SLARequirementRepository slaRequirementRepository) {
        this.slaRequirementRepository = slaRequirementRepository;
    }

    @Override
    public SLARequirement createRequirement(SLARequirement requirement) {
        validateRequirement(requirement);
        if (slaRequirementRepository.existsByRequirementName(requirement.getRequirementName())) {
            throw new IllegalArgumentException("SLA requirement name must be unique");
        }
        return slaRequirementRepository.save(requirement);
    }

    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement requirement) {
        SLARequirement existing = slaRequirementRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SLA requirement not found"));
        if (slaRequirementRepository.existsByRequirementName(requirement.getRequirementName()) 
            && !requirement.getRequirementName().equals(existing.getRequirementName())) {
            throw new IllegalArgumentException("SLA requirement name must be unique");
        }
        validateRequirement(requirement);
        existing.setRequirementName(requirement.getRequirementName());
        existing.setDescription(requirement.getDescription());
        existing.setMaxDeliveryDays(requirement.getMaxDeliveryDays());
        existing.setMinQualityScore(requirement.getMinQualityScore());
        return slaRequirementRepository.save(existing);
    }

    @Override
    public void deactivateRequirement(Long id) {
        SLARequirement requirement = slaRequirementRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("SLA requirement not found"));
        requirement.setActive(false);
        slaRequirementRepository.save(requirement);
    }

    private void validateRequirement(SLARequirement req) {
        if (req.getMaxDeliveryDays() == null || req.getMaxDeliveryDays() <= 0) {
            throw new IllegalArgumentException("Max delivery days must be greater than 0");
        }
        if (req.getMinQualityScore() == null || req.getMinQualityScore() < 0 || req.getMinQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100");
        }
    }
}

package com.example.demo.service.impl;

import com.example.demo.model.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SLARequirementServiceImpl implements SLARequirementService {
    private final SLARequirementRepository slaRequirementRepository;
    
    public SLARequirementServiceImpl(SLARequirementRepository slaRequirementRepository) {
        this.slaRequirementRepository = slaRequirementRepository;
    }
    
    @Override
    public SLARequirement createRequirement(SLARequirement requirement) {
        if (requirement.getMaxDeliveryDays() <= 0) {
            throw new IllegalArgumentException("Max delivery days must be > 0");
        }
        if (requirement.getMinQualityScore() < 0 || requirement.getMinQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100");
        }
        if (slaRequirementRepository.existsByRequirementName(requirement.getRequirementName())) {
            throw new IllegalArgumentException("Requirement name must be unique");
        }
        return slaRequirementRepository.save(requirement);
    }
    
    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement requirement) {
        SLARequirement existing = slaRequirementRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Requirement not found"));
        
        if (requirement.getRequirementName() != null 
            && !requirement.getRequirementName().equals(existing.getRequirementName())
            && slaRequirementRepository.existsByRequirementName(requirement.getRequirementName())) {
            throw new IllegalArgumentException("Requirement name must be unique");
        }
        
        if (requirement.getRequirementName() != null) existing.setRequirementName(requirement.getRequirementName());
        if (requirement.getDescription() != null) existing.setDescription(requirement.getDescription());
        if (requirement.getMaxDeliveryDays() != null) existing.setMaxDeliveryDays(requirement.getMaxDeliveryDays());
        if (requirement.getMinQualityScore() != null) existing.setMinQualityScore(requirement.getMinQualityScore());
        
        return slaRequirementRepository.save(existing);
    }
    
    @Override
    public SLARequirement getRequirementById(Long id) {
        return slaRequirementRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Requirement not found"));
    }
    
    @Override
    public List<SLARequirement> getAllRequirements() {
        return slaRequirementRepository.findAll();
    }
    
    @Override
    public void deactivateRequirement(Long id) {
        SLARequirement requirement = getRequirementById(id);
        requirement.setActive(false);
        slaRequirementRepository.save(requirement);
    }
}
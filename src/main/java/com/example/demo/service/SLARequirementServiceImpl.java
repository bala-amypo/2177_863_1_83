package com.example.demo.service;

import com.example.demo.entity.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SLARequirementServiceImpl
        implements SLARequirementService {

    private final SLARequirementRepository repository;

    public SLARequirementServiceImpl(SLARequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public SLARequirement createRequirement(SLARequirement req) {

        if (repository.existsByRequirementName(
                req.getRequirementName())) {
            throw new IllegalArgumentException("unique");
        }

        if (req.getMaxDeliveryDays() <= 0) {
            throw new IllegalArgumentException("Max delivery days");
        }

        if (req.getMinQualityScore() < 0 ||
            req.getMinQualityScore() > 100) {
            throw new IllegalArgumentException(
                    "Quality score between 0 and 100");
        }

        req.setActive(true);
        return repository.save(req);
    }

    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement req) {

        SLARequirement existing = getRequirementById(id);

        existing.setActive(req.getActive());
        return repository.save(existing);
    }

    @Override
    public SLARequirement getRequirementById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    @Override
    public List<SLARequirement> getAllRequirements() {
        return repository.findAll();
    }

    @Override
    public void deactivateRequirement(Long id) {
        SLARequirement sla = getRequirementById(id);
        sla.setActive(false);
        repository.save(sla);
    }
}

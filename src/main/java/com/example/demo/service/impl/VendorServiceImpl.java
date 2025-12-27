package com.example.demo.service.impl;

import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorTierServiceImpl implements VendorTierService {

    private final VendorTierRepository vendorTierRepository;

    public VendorTierServiceImpl(VendorTierRepository vendorTierRepository) {
        this.vendorTierRepository = vendorTierRepository;
    }

    @Override
    public VendorTier determineTier(Double score) {
        List<VendorTier> tiers = vendorTierRepository.findAll();

        for (VendorTier tier : tiers) {
            if (score >= tier.getMinOverallScore()) {
                return tier;
            }
        }
        return null;
    }
}

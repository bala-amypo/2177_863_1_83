package com.example.demo.service;

import com.example.demo.model.VendorTier;
import java.util.List;

public interface VendorTierService {
    VendorTier createTier(VendorTier tier);
    VendorTier updateTier(Long id, VendorTier tier);
    List<VendorTier> getAllTiers();
    void deactivateTier(Long id);
}
package com.example.demo.service;

import com.example.demo.model.VendorTier;

public interface VendorTierService {
    VendorTier createTier(VendorTier tier);     // Test #80, #81
    String assignTier(Long vendorId);           // Test #50, #55
    void deactivateTier(Long id);               // Test #82
}

package com.example.demo.controller;

import com.example.demo.model.VendorTier;
import com.example.demo.service.VendorTierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/tiers")
@Tag(name = "Vendor Tier API")
public class VendorTierController {

    private final VendorTierService service;

    public VendorTierController(VendorTierService service) {
        this.service = service;
    }

    @PostMapping
    public VendorTier create(@RequestBody VendorTier tier) {
        return service.createTier(tier);
    }

    @PutMapping("/{id}")
    public VendorTier update(@PathVariable Long id,
                             @RequestBody VendorTier tier) {
        return service.updateTier(id, tier);
    }

    @GetMapping("/{id}")
    public VendorTier getById(@PathVariable Long id) {
        return service.getTierById(id);
    }

    @GetMapping
    public List<VendorTier> getAll() {
        return service.getAllTiers();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateTier(id);
    }
}

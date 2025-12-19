package com.example.demo.controller;

import com.example.demo.entity.VendorTier;
import com.example.demo.service.VendorTierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiers")
@Tag(name = "Vendor Tier API")
public class VendorTierController {

    private final VendorTierService tierService;

    public VendorTierController(VendorTierService tierService) {
        this.tierService = tierService;
    }

    @PostMapping
    public VendorTier create(@RequestBody VendorTier tier) {
        return tierService.create(tier);
    }

    @PutMapping("/{id}")
    public VendorTier update(@PathVariable Long id, @RequestBody VendorTier tier) {
        return tierService.update(id, tier);
    }

    @GetMapping("/{id}")
    public VendorTier getById(@PathVariable Long id) {
        return tierService.getById(id);
    }

    @GetMapping
    public List<VendorTier> getAll() {
        return tierService.getAll();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        tierService.deactivate(id);
    }
}

package com.example.demo.controller;

import com.example.demo.model.VendorTier;
import com.example.demo.service.VendorTierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-tiers")
@Tag(name = "Vendor Tiers", description = "APIs for managing vendor tiers")
public class VendorTierController {

    @Autowired
    private VendorTierService service;

    @PostMapping
    @Operation(summary = "Create vendor tier")
    public ResponseEntity<VendorTier> createTier(@RequestBody VendorTier tier) {
        return ResponseEntity.ok(service.createTier(tier));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update vendor tier")
    public ResponseEntity<VendorTier> updateTier(@PathVariable Long id, @RequestBody VendorTier tier) {
        return ResponseEntity.ok(service.updateTier(id, tier));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vendor tier by ID")
    public ResponseEntity<List<VendorTier>> getAllTiers() {
        return ResponseEntity.ok(service.getAllTiers());
    }

    @GetMapping
    @Operation(summary = "Get all vendor tiers")
    public ResponseEntity<List<VendorTier>> getAllTiers2() {
        return ResponseEntity.ok(service.getAllTiers());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deactivate vendor tier")
    public ResponseEntity<Void> deactivateTier(@PathVariable Long id) {
        service.deactivateTier(id);
        return ResponseEntity.ok().build();
    }
}
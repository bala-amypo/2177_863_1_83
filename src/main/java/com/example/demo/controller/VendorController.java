package com.example.demo.controller;

import com.example.demo.entity.Vendor;
import com.example.demo.service.VendorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@Tag(name = "Vendor API")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping
    public Vendor createVendor(@RequestBody Vendor vendor) {
        return vendorService.createVendor(vendor);
    }

    @PutMapping("/{id}")
    public Vendor updateVendor(@PathVariable Long id, @RequestBody Vendor vendor) {
        return vendorService.updateVendor(id, vendor);
    }

    @GetMapping("/{id}")
    public Vendor getVendor(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateVendor(@PathVariable Long id) {
        vendorService.deactivateVendor(id);
    }
}

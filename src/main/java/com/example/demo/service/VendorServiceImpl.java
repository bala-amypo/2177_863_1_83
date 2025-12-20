package com.example.demo.service;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository repository;

    public VendorServiceImpl(VendorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        if (repository.existsByName(vendor.getName())) {
            throw new IllegalArgumentException("unique");
        }
        vendor.setActive(true);
        return repository.save(vendor);
    }

    @Override
    public Vendor getVendorById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    @Override
    public List<Vendor> getAllVendors() {
        return repository.findAll();
    }

    @Override
    public void deactivateVendor(Long id) {
        Vendor vendor = getVendorById(id);
        vendor.setActive(false);
        repository.save(vendor);
    }
}

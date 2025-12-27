package com.example.demo.repository;

import com.example.demo.model.VendorPerformanceScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorPerformanceScoreRepository
        extends JpaRepository<VendorPerformanceScore, Long> {

    @Query("""
           SELECT vps
           FROM VendorPerformanceScore vps
           WHERE vps.vendor.id = :vendorId
           ORDER BY vps.calculatedAt DESC
           """)
    List<VendorPerformanceScore>
    findByVendorOrderByCalculatedAtDesc(@Param("vendorId") Long vendorId);
}

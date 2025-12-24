package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.*;

import java.util.List;

public interface DeliveryEvaluationRepository extends JpaRepository<DeliveryEvaluation, Long> {

    List<DeliveryEvaluation> findByVendorId(Long vendorId);

    List<DeliveryEvaluation> findBySlaRequirementId(Long slaId);

     @Query
    @Query("""
           SELECT d FROM DeliveryEvaluation d
           WHERE d.vendor = :vendor
           AND d.score >= :minScore
           """)
    List<DeliveryEvaluation> findHighQualityDeliveries(
            @Param("vendor") Vendor vendor,
            @Param("minScore") Double minScore
    );

    @Query
    @Query("""
           SELECT d FROM DeliveryEvaluation d
           WHERE d.slaRequirement = :sla
           AND d.onTime = true
           """)
    List<DeliveryEvaluation> findOnTimeDeliveries(
            @Param("sla") SLARequirement sla
    );
}

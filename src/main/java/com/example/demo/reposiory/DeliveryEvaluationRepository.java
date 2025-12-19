package com.example.demo.repository;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.entity.SLARequirement;
import com.example.demo.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryEvaluationRepository extends JpaRepository<DeliveryEvaluation, Long> {

    List<DeliveryEvaluation> findByVendorId(Long vendorId);

    List<DeliveryEvaluation> findBySlaRequirementId(Long slaId);

    @Query("""
           SELECT COUNT(de)
           FROM DeliveryEvaluation de
           WHERE de.vendor = :vendor
           AND de.score >= :minScore
           """)
    Long findHighQualityDeliveries(Vendor vendor, Double minScore);

    @Query("""
           SELECT COUNT(de)
           FROM DeliveryEvaluation de
           WHERE de.slaRequirement = :sla
           """)
    Long findOnTimeDeliveries(SLARequirement sla);
}

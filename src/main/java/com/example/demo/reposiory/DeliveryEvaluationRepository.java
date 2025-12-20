package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface DeliveryEvaluationRepository
        extends JpaRepository<DeliveryEvaluation, Long> {

    List<DeliveryEvaluation> findByVendorId(Long vendorId);

    List<DeliveryEvaluation> findBySlaRequirementId(Long slaId);

    @Query("""
           SELECT de FROM DeliveryEvaluation de
           WHERE de.vendor = :vendor AND de.qualityScore >= :minScore
           """)
    List<DeliveryEvaluation> findHighQualityDeliveries(
            Vendor vendor, Double minScore);

    @Query("""
           SELECT de FROM DeliveryEvaluation de
           WHERE de.slaRequirement = :sla AND de.meetsDeliveryTarget = true
           """)
    List<DeliveryEvaluation> findOnTimeDeliveries(SLARequirement sla);
}

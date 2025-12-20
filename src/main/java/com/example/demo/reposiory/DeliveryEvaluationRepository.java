package com.example.demo.repository;

import com.example.demo.entity.DeliveryEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeliveryEvaluationRepository extends JpaRepository<DeliveryEvaluation, Long> {

    // Find evaluations by vendor ID
    List<DeliveryEvaluation> findByVendor_Id(Long vendorId);

    // Find evaluations by SLA requirement ID
    List<DeliveryEvaluation> findBySlaRequirement_Id(Long slaRequirementId);
}

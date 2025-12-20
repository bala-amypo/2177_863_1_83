package com.example.demo.repository;

import com.example.demo.model.DeliveryEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeliveryEvaluationRepository extends JpaRepository<DeliveryEvaluation, Long> {
    List<DeliveryEvaluation> findByVendor_Id(Long vendorId);
    List<DeliveryEvaluation> findBySlaRequirement_Id(Long slaRequirementId);
}
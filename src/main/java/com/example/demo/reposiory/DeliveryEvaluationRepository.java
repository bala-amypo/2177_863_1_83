package com.example.demo.repository;

import com.example.demo.entity.DeliveryEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeliveryEvaluationRepository
        extends JpaRepository<DeliveryEvaluation, Long> {

    List<DeliveryEvaluation> findByVendor_Id(Long vendorId);

    List<DeliveryEvaluation> findBySlaRequirement_Id(Long slaRequirementId);
}


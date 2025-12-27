package com.example.demo.repository;

import com.example.demo.model.DeliveryEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeliveryEvaluationRepository extends JpaRepository<DeliveryEvaluation, Long> {
    List<DeliveryEvaluation> findBySlaRequirementIdAndMeetsDeliveryTargetTrue(Long slaRequirementId);
}

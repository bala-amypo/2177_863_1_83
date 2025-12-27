package com.example.demo.service;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.SLARequirement;
import com.example.demo.model.Vendor;
import java.util.List;

public interface DeliveryEvaluationService {
    DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation);
    List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId);
    List<DeliveryEvaluation> getEvaluationsForRequirement(Long slaId);
    
    // HQL methods called directly in tests #75, #76
    List<DeliveryEvaluation> findHighQualityDeliveries(Vendor vendor, Double threshold);
    List<DeliveryEvaluation> findOnTimeDeliveries(SLARequirement sla);
}

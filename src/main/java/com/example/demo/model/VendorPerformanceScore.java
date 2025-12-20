package com.example.demo.entity;

import com.example.demo.entity.Vendor;
import com.example.demo.entity.DeliveryEvaluation;

public class VendorPerformanceScore {

    private Vendor vendor;
    private DeliveryEvaluation evaluation;
    private Boolean meetsDeliveryTarget;
    private Boolean meetsQualityTarget;

    public VendorPerformanceScore() {
    }

    public VendorPerformanceScore(Vendor vendor, DeliveryEvaluation evaluation,
                                  Boolean meetsDeliveryTarget, Boolean meetsQualityTarget) {
        this.vendor = vendor;
        this.evaluation = evaluation;
        this.meetsDeliveryTarget = meetsDeliveryTarget;
        this.meetsQualityTarget = meetsQualityTarget;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public DeliveryEvaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(DeliveryEvaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Boolean getMeetsDeliveryTarget() {
        return meetsDeliveryTarget;
    }

    public void setMeetsDeliveryTarget(Boolean meetsDeliveryTarget) {
        this.meetsDeliveryTarget = meetsDeliveryTarget;
    }

    public Boolean getMeetsQualityTarget() {
        return meetsQualityTarget;
    }

    public void setMeetsQualityTarget(Boolean meetsQualityTarget) {
        this.meetsQualityTarget = meetsQualityTarget;
    }
}

package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_performance_scores")
public class VendorPerformanceScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "vendor_id")
    private Long vendorId;
    
    @Column(name = "delivery_compliance_rate")
    private Double deliveryComplianceRate;
    
    @Column(name = "quality_compliance_rate")
    private Double qualityComplianceRate;
    
    @Column(name = "overall_score")
    private Double overallScore;
    
    @Column(name = "calculated_at")
    private LocalDateTime calculatedAt = LocalDateTime.now();

    public VendorPerformanceScore() {}

    public VendorPerformanceScore(Vendor vendor, Double deliveryRate, Double qualityRate, Double overall) {
        this.vendorId = vendor.getId();
        this.deliveryComplianceRate = deliveryRate;
        this.qualityComplianceRate = qualityRate;
        this.overallScore = overall;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }
    public Double getDeliveryComplianceRate() { return deliveryComplianceRate; }
    public void setDeliveryComplianceRate(Double deliveryComplianceRate) { this.deliveryComplianceRate = deliveryComplianceRate; }
    public Double getQualityComplianceRate() { return qualityComplianceRate; }
    public void setQualityComplianceRate(Double qualityComplianceRate) { this.qualityComplianceRate = qualityComplianceRate; }
    public Double getOverallScore() { return overallScore; }
    public void setOverallScore(Double overallScore) { this.overallScore = overallScore; }
    public LocalDateTime getCalculatedAt() { return calculatedAt; }
    public void setCalculatedAt(LocalDateTime calculatedAt) { this.calculatedAt = calculatedAt; }
    
    public Double getOnTimePercentage() { return deliveryComplianceRate; }
    public Double getQualityCompliancePercentage() { return qualityComplianceRate; }
}

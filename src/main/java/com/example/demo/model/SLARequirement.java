package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sla_requirements", uniqueConstraints = @UniqueConstraint(columnNames = "requirementName"))
public class SLARequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String requirementName;

    private String description;

    @Column(nullable = false)
    private Integer maxDeliveryDays;

    @Column(nullable = false)
    private Double minQualityScore;

    @Column(nullable = false)
    private Boolean active = true;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaxDeliveryDays() {
        return maxDeliveryDays;
    }

    public void setMaxDeliveryDays(Integer maxDeliveryDays) {
        this.maxDeliveryDays = maxDeliveryDays;
    }

    public Double getMinQualityScore() {
        return minQualityScore;
    }

    public void setMinQualityScore(Double minQualityScore) {
        this.minQualityScore = minQualityScore;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "delivery_evaluations")
public class DeliveryEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "sla_id", nullable = false)
    private SLARequirement slaRequirement;

    private Integer actualDeliveryDays;
    private Double qualityScore;
    private LocalDate evaluationDate;

    private Boolean meetsDeliveryTarget;
    private Boolean meetsQualityTarget;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SLARequirement getSlaRequirement() {
        return slaRequirement;
    }

    public void setSlaRequirement(SLARequirement slaRequirement) {
        this.slaRequirement = slaRequirement;
    }

    public Integer getActualDeliveryDays() {
        return actualDeliveryDays;
    }

    public void setActualDeliveryDays(Integer actualDeliveryDays) {
        this.actualDeliveryDays = actualDeliveryDays;
    }

    public Double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDate evaluationDate) {
        this.evaluationDate = evaluationDate;
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

package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_performance_scores")
public class VendorPerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    private Double onTimePercentage;
    private Double qualityCompliancePercentage;
    private Double overallScore;

    private LocalDateTime calculatedAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getOnTimePercentage() {
        return onTimePercentage;
    }

    public void setOnTimePercentage(Double onTimePercentage) {
        this.onTimePercentage = onTimePercentage;
    }

    public Double getQualityCompliancePercentage() {
        return qualityCompliancePercentage;
    }

    public void setQualityCompliancePercentage(Double qualityCompliancePercentage) {
        this.qualityCompliancePercentage = qualityCompliancePercentage;
    }

    public Double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
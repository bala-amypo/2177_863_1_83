package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "delivery_evaluations")
public class DeliveryEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Vendor vendor;

    @ManyToOne(optional = false)
    private SLARequirement slaRequirement;

    private Integer actualDeliveryDays;
    private Double qualityScore;
    private LocalDate evaluationDate;

    private Boolean meetsDeliveryTarget;
    private Boolean meetsQualityTarget;

    public DeliveryEvaluation() {}

    public DeliveryEvaluation(Vendor vendor,
                              SLARequirement slaRequirement,
                              Integer actualDeliveryDays,
                              Double qualityScore,
                              LocalDate evaluationDate) {
        this.vendor = vendor;
        this.slaRequirement = slaRequirement;
        this.actualDeliveryDays = actualDeliveryDays;
        this.qualityScore = qualityScore;
        this.evaluationDate = evaluationDate;
    }

    public Long getId() { return id; }
    public Vendor getVendor() { return vendor; }
    public SLARequirement getSlaRequirement() { return slaRequirement; }
    public Integer getActualDeliveryDays() { return actualDeliveryDays; }
    public Double getQualityScore() { return qualityScore; }

    public Boolean isMeetsDeliveryTarget() { return meetsDeliveryTarget; }
    public Boolean isMeetsQualityTarget() { return meetsQualityTarget; }

    public void setMeetsDeliveryTarget(Boolean meetsDeliveryTarget) {
        this.meetsDeliveryTarget = meetsDeliveryTarget;
    }

    public void setMeetsQualityTarget(Boolean meetsQualityTarget) {
        this.meetsQualityTarget = meetsQualityTarget;
    }
}

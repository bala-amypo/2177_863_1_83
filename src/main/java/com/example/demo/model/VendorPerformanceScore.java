package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vendor_performance_scores")
public class VendorPerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "evaluation_id", nullable = false)
    private DeliveryEvaluation evaluation;

    @Column(name = "meets_delivery_target")
    private Boolean meetsDeliveryTarget;

    @Column(name = "meets_quality_target")
    private Boolean meetsQualityTarget;

    @Column(name = "score_date")
    private LocalDate scoreDate;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }

    public DeliveryEvaluation getEvaluation() { return evaluation; }
    public void setEvaluation(DeliveryEvaluation evaluation) { this.evaluation = evaluation; }

    public Boolean getMeetsDeliveryTarget() { return meetsDeliveryTarget; }
    public void setMeetsDeliveryTarget(Boolean meetsDeliveryTarget) { this.meetsDeliveryTarget = meetsDeliveryTarget; }

    public Boolean getMeetsQualityTarget() { return meetsQualityTarget; }
    public void setMeetsQualityTarget(Boolean meetsQualityTarget) { this.meetsQualityTarget = meetsQualityTarget; }

    public LocalDate getScoreDate() { return scoreDate; }
    public void setScoreDate(LocalDate scoreDate) { this.scoreDate = scoreDate; }
}

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vendor_performance_scores")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VendorPerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    @JsonIgnoreProperties({"deliveryEvaluations"})
    private Vendor vendor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluation_id", nullable = false)
    @JsonIgnoreProperties({"vendor", "slaRequirement"})
    private DeliveryEvaluation evaluation;

    @Column(name = "meets_delivery_target")
    private Boolean meetsDeliveryTarget;

    @Column(name = "meets_quality_target")
    private Boolean meetsQualityTarget;

    @Column(name = "score_date")
    private LocalDate scoreDate;

    @Column(name = "score") // Add this column in the DB as well
    private Double score;

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

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
}

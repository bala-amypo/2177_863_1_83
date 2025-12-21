package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "sla_requirements")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "deliveryEvaluations"})
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

    private Boolean active = true;

    public SLARequirement() {}

    public SLARequirement(String requirementName, String description,
                          Integer maxDeliveryDays, Double minQualityScore, Boolean active) {
        this.requirementName = requirementName;
        this.description = description;
        this.maxDeliveryDays = maxDeliveryDays;
        this.minQualityScore = minQualityScore;
        this.active = active;
    }

    
    public Long getId() { return id; }
    public String getRequirementName() { return requirementName; }
    public String getDescription() { return description; }
    public Integer getMaxDeliveryDays() { return maxDeliveryDays; }
    public Double getMinQualityScore() { return minQualityScore; }
    public Boolean getActive() { return active; }

    
    public void setRequirementName(String requirementName) { this.requirementName = requirementName; }
    public void setDescription(String description) { this.description = description; }
    public void setMaxDeliveryDays(Integer maxDeliveryDays) { this.maxDeliveryDays = maxDeliveryDays; }
    public void setMinQualityScore(Double minQualityScore) { this.minQualityScore = minQualityScore; }
    public void setActive(Boolean active) { this.active = active; }
}

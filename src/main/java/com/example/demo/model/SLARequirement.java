package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "sla_requirements",
    uniqueConstraints = @UniqueConstraint(columnNames = "requirementName")
)
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

    // ✅ No-args constructor (required by JPA)
    public SLARequirement() {
    }

    // ✅ Correct parameterized constructor
    public SLARequirement(Long id,
                          String requirementName,
                          String description,
                          Integer maxDeliveryDays,
                          Double minQualityScore,
                          Boolean active) {

        this.id = id;
        this.requirementName = requirementName;
        this.description = description;
        this.maxDeliveryDays = maxDeliveryDays;
        this.minQualityScore = minQualityScore;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
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

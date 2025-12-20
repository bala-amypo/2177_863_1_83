package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "sla_requirements",
    uniqueConstraints = @UniqueConstraint(columnNames = "requirement_name")
)
public class SLARequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "requirement_name", nullable = false, unique = true)
    private String requirementName;

    private String description;

    private Integer maxDeliveryDays;
    private Double minQualityScore;

    private Boolean active = true;

    public SLARequirement() {}

    public SLARequirement(String requirementName,
                          String description,
                          Integer maxDeliveryDays,
                          Double minQualityScore) {
        this.requirementName = requirementName;
        this.description = description;
        this.maxDeliveryDays = maxDeliveryDays;
        this.minQualityScore = minQualityScore;
    }

    public Long getId() {
        return id;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

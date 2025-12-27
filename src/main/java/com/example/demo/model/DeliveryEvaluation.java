package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class DeliveryEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long slaRequirementId;
    private boolean meetsDeliveryTarget;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSlaRequirementId() { return slaRequirementId; }
    public void setSlaRequirementId(Long slaRequirementId) { this.slaRequirementId = slaRequirementId; }

    public boolean isMeetsDeliveryTarget() { return meetsDeliveryTarget; }
    public void setMeetsDeliveryTarget(boolean meetsDeliveryTarget) { this.meetsDeliveryTarget = meetsDeliveryTarget; }
}

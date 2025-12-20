package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vendor_tiers")
public class VendorTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tierName;

    @Column(nullable = false)
    private Double minScore;

    private Boolean active = true;

    public VendorTier() {
    }

    public VendorTier(String tierName, Double minScore) {
        this.tierName = tierName;
        this.minScore = minScore;
    }

    public Long getId() {
        return id;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public Double getMinScoreThreshold() {
        return minScore;
    }

    public void setMinScore(Double minScore) {
        this.minScore = minScore;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

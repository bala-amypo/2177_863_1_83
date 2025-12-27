package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vendor_tiers")
public class VendorTier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "tier_name", unique = true, nullable = false)
    private String tierName;
    
    @Column(name = "min_overall_score")
    private Double minOverallScore;
    
    private String description;
    
    private boolean active = true;

    public VendorTier() {}

    public VendorTier(String tierName, Double minOverallScore, String description) {
        this.tierName = tierName;
        this.minOverallScore = minOverallScore;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTierName() { return tierName; }
    public void setTierName(String tierName) { this.tierName = tierName; }
    public Double getMinOverallScore() { return minOverallScore; }
    public void setMinOverallScore(Double minOverallScore) { this.minOverallScore = minOverallScore; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

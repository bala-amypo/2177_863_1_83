package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "vendor_tiers",
    uniqueConstraints = @UniqueConstraint(columnNames = "tier_name")
)
public class VendorTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tier_name", nullable = false, unique = true)
    private String tierName;

    private Double minScore;
    private Boolean active = true;

    public VendorTier() {}

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

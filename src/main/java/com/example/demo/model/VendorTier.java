package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vendor_tiers",
       uniqueConstraints = @UniqueConstraint(columnNames = "tierName"))
public class VendorTier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String tierName;
	private Double minScoreThreshold;
	private String description;
	private Boolean active = true;

	public VendorTier() {}

	public VendorTier(String tierName, Double minScoreThreshold, String description) {
		this.tierName = tierName;
		this.minScoreThreshold = minScoreThreshold;
		this.description = description;
		this.active = true;
	}

	public Long getId() {
		return id;
	}
	public Double getMinScoreThreshold() {
		return minScoreThreshold;
	}
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}

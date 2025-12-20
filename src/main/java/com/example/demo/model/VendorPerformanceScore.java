package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_performance_scores")
public class VendorPerformanceScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Vendor vendor;

	private Double onTimePercentage;
	private Double qualityCompliancePercentage;
	private Double overallScore;
	private LocalDateTime calculatedAt;

	public VendorPerformanceScore() {}

	public VendorPerformanceScore(Vendor vendor, Double overallScore) {
		this.vendor = vendor;
		this.overallScore = overallScore;
		this.calculatedAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public Double getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(Double overallScore) {
		this.overallScore = overallScore;
	}
}

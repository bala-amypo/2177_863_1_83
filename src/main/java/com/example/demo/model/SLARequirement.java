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

	public SLARequirement() {}

	public SLARequirement(String requirementName,
	                      String description,
	                      Integer maxDeliveryDays,
	                      Double minQualityScore) {
		this.requirementName = requirementName;
		this.description = description;
		this.maxDeliveryDays = maxDeliveryDays;
		this.minQualityScore = minQualityScore;
		this.active = true;
	}

	public Long getId() {
		return id;
	}
	public Integer getMaxDeliveryDays() {
		return maxDeliveryDays;
	}
	public Double getMinQualityScore() {
		return minQualityScore;
	}
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}

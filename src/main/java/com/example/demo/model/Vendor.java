package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendors", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	private String contactEmail;
	private String contactPhone;

	@Column(nullable = false)
	private Boolean active = true;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Vendor() {}

	public Vendor(String name, String contactEmail, String contactPhone) {
		this.name = name;
		this.contactEmail = contactEmail;
		this.contactPhone = contactPhone;
		this.active = true;
	}

	@PrePersist
	void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Boolean getActive() {
		return active;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}

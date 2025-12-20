package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_performance_scores")
public class VendorPerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @Column(nullable = false)
    private Double score;

    @Column(nullable = false)
    private LocalDateTime calculatedAt;

    public VendorPerformanceScore() {
    }

    public VendorPerformanceScore(Vendor vendor, Double score) {
        this.vendor = vendor;
        this.score = score;
        this.calculatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Double getScore() {
        return score;
    }
    public void setOverallScore(double overallScore) {
        this.score = overallScore;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}

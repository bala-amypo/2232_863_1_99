package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AssetDisposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double disposalValue;
    private String status;

    @ManyToOne
    private Asset asset;

    @ManyToOne
    private User approvedBy;

    private LocalDateTime createdAt;

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public Double getDisposalValue() {
        return disposalValue;
    }

    public String getStatus() {
        return status;
    }

    public Asset getAsset() {
        return asset;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDisposalValue(Double disposalValue) {
        this.disposalValue = disposalValue;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

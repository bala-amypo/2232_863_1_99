package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "asset_disposals")
public class AssetDisposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "asset_id", nullable = false)
    @JsonIgnore
    private Asset asset;

    @Column(name = "disposal_method", nullable = false)
    private String disposalMethod;

    @Column(name = "disposal_value", nullable = false)
    private Double disposalValue;

    @Column(name = "disposal_date", nullable = false)
    private LocalDate disposalDate;

    @ManyToOne
    @JoinColumn(name = "approved_by", nullable = true)
    @JsonIgnore
    private User approvedBy;


    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public AssetDisposal() {}

    // ✅ Automatically set createdAt before insert
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Asset getAsset() {
        return asset;
    }
    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getDisposalMethod() {
        return disposalMethod;
    }
    public void setDisposalMethod(String disposalMethod) {
        this.disposalMethod = disposalMethod;
    }

    public Double getDisposalValue() {
        return disposalValue;
    }
    public void setDisposalValue(Double disposalValue) {
        this.disposalValue = disposalValue;
    }

    public LocalDate getDisposalDate() {
        return disposalDate;
    }
    public void setDisposalDate(LocalDate disposalDate) {
        this.disposalDate = disposalDate;
    }

    public User getApprovedBy() {
        return approvedBy;
    }
    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

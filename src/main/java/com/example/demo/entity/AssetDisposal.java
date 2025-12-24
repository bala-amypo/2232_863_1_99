package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_disposals")
public class AssetDisposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String disposalMethod;
    private Double disposalValue;
    private LocalDate disposalDate;
    private LocalDateTime createdAt;

    @OneToOne
    private Asset asset;

    @ManyToOne
    private User approvedBy;

    public AssetDisposal() {}

    public AssetDisposal(Asset asset, String disposalMethod,
                         Double disposalValue, LocalDate disposalDate, User approvedBy) {
        this.asset = asset;
        this.disposalMethod = disposalMethod;
        this.disposalValue = disposalValue;
        this.disposalDate = disposalDate;
        this.approvedBy = approvedBy;
        this.createdAt = LocalDateTime.now();
    }

    // getters and setters
    public Long getId() { return id; }
    public Asset getAsset() { return asset; }
    public void setApprovedBy(User approvedBy) { this.approvedBy = approvedBy; }
}

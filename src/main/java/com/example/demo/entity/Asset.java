package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assetTag;
    private String assetName;
    private Double purchaseCost;
    private String status;
    private LocalDateTime createdAt;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private DepreciationRule depreciationRule;
}

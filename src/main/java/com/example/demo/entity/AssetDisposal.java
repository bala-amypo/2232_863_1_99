package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_disposals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "depreciation_rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepreciationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private String method;
    private Integer usefulLifeYears;
    private Double salvageValue;
    private LocalDateTime createdAt;
}

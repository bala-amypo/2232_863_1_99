package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(
    name = "depreciation_rules",
    uniqueConstraints = @UniqueConstraint(columnNames = "rule_name")
)
public class DepreciationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rule_name", nullable = false, unique = true)
    private String ruleName;

    private String method;

    @Min(1)
    private int usefulLifeYears;

    @PositiveOrZero
    private Double salvageValue;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public int getUsefulLifeYears() { return usefulLifeYears; }
    public void setUsefulLifeYears(int usefulLifeYears) {
        this.usefulLifeYears = usefulLifeYears;
    }

    public Double getSalvageValue() { return salvageValue; }
    public void setSalvageValue(Double salvageValue) {
        this.salvageValue = salvageValue;
    }
}

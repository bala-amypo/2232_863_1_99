package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "depreciation_rules", uniqueConstraints = @UniqueConstraint(columnNames = "ruleName"))
public class DepreciationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private String method;
    private Integer usefulLifeYears;
    private Double salvageValue;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "depreciationRule")
    private Set<Asset> assets;

    public DepreciationRule() {}

    public DepreciationRule(String ruleName, String method, Integer usefulLifeYears, Double salvageValue) {
        this.ruleName = ruleName;
        this.method = method;
        this.usefulLifeYears = usefulLifeYears;
        this.salvageValue = salvageValue;
        this.createdAt = LocalDateTime.now();
    }

    // getters and setters
    public Long getId() { return id; }
    public String getRuleName() { return ruleName; }
    public String getMethod() { return method; }
    public Integer getUsefulLifeYears() { return usefulLifeYears; }
    public Double getSalvageValue() { return salvageValue; }

    public void setId(Long id) { this.id = id; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }
    public void setMethod(String method) { this.method = method; }
    public void setUsefulLifeYears(Integer usefulLifeYears) { this.usefulLifeYears = usefulLifeYears; }
    public void setSalvageValue(Double salvageValue) { this.salvageValue = salvageValue; }
}

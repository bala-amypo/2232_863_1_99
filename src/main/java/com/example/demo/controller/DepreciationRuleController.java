package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/depreciation-rules")
public class DepreciationRuleController {

    private final DepreciationRuleRepository repository;

    public DepreciationRuleController(
            DepreciationRuleRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public DepreciationRule create(@RequestBody DepreciationRule rule) {
        return repository.save(rule);
    }
}

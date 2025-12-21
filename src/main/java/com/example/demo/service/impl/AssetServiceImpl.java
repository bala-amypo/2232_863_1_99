package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Vendor;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository AssetRepository;
    private final VendorRepository vendorRepository;
    private final DepreciationRuleRepository ruleRepository;

    public AssetServiceImpl(AssetRepository AssetRepository,
                            VendorRepository vendorRepository,
                            DepreciationRuleRepository ruleRepository) {
        this.AssetRepository = AssetRepository;
        this.vendorRepository = vendorRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public Asset createAsset(Long vendorId, Long ruleId, Asset Asset) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

        DepreciationRule rule = ruleRepository.findById(ruleId)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        if (Asset.getPurchaseCost() <= 0) {
            throw new IllegalArgumentException("Purchase cost must be greater than 0");
        }

        if (AssetRepository.existsByAssetTag(Asset.getAssetTag())) {
            throw new IllegalArgumentException("Asset tag already exists");
        }

        Asset.setVendor(vendor);
        Asset.setDepreciationRule(rule);
        Asset.setStatus("ACTIVE");
        Asset.setCreatedAt(LocalDateTime.now());

        return AssetRepository.save(Asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return AssetRepository.findAll();
    }

    @Override
    public List<Asset> getAssetsByStatus(String status) {
        return AssetRepository.findByStatus(status);
    }

    @Override
    public Asset getAssetById(Long id) {
        return AssetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
    }
}

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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final VendorRepository vendorRepository;
    private final DepreciationRuleRepository depreciationRuleRepository;

    public AssetServiceImpl(AssetRepository assetRepository,VendorRepository vendorRepository,DepreciationRuleRepository depreciationRuleRepository) {
        this.assetRepository = assetRepository;
        this.vendorRepository = vendorRepository;
        this.depreciationRuleRepository = depreciationRuleRepository;
    }

    @Override
    public Asset createAsset(Long vendorId, Long ruleId, Asset asset) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

        DepreciationRule rule = depreciationRuleRepository.findById(ruleId)
                .orElseThrow(() -> new ResourceNotFoundException("Depreciation rule not found"));

        if (asset.getAssetTag() == null || asset.getAssetTag().isBlank()) {
            throw new IllegalArgumentException("Asset tag is required");
        }

        if (asset.getPurchaseCost() == null || asset.getPurchaseCost() <= 0) {
            throw new IllegalArgumentException("Purchase cost must be greater than 0");
        }

        if (assetRepository.existsByAssetTag(asset.getAssetTag())) {
            throw new IllegalArgumentException("Asset tag already exists");
        }

        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);

        if (asset.getStatus() == null) {
            asset.setStatus("ACTIVE");
        }

        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAssetsByStatus(String status) {
        return assetRepository.findByStatus(status);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAsset(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
    }
}

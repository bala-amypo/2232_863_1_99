package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.AssetService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final VendorRepository vendorRepository;
    private final DepreciationRuleRepository depreciationRuleRepository;

    public AssetServiceImpl(
            AssetRepository assetRepository,
            VendorRepository vendorRepository,
            DepreciationRuleRepository depreciationRuleRepository) {

        this.assetRepository = assetRepository;
        this.vendorRepository = vendorRepository;
        this.depreciationRuleRepository = depreciationRuleRepository;
    }

    @Override
    public Asset createAsset(
            Asset asset,
            Long vendorId,
            Long depreciationRuleId) {

        if (asset.getPurchaseCost() != null &&
                asset.getPurchaseCost() < 0) {
            throw new IllegalArgumentException(
                    "Purchase cost must be positive");
        }

        if (assetRepository.existsByAssetTag(asset.getAssetTag())) {
            throw new DataIntegrityViolationException(
                    "Duplicate asset tag");
        }

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Vendor not found"));

        DepreciationRule rule =
                depreciationRuleRepository.findById(depreciationRuleId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Rule not found"));

        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);
        asset.setStatus("ACTIVE");

        return assetRepository.save(asset);
    }
}

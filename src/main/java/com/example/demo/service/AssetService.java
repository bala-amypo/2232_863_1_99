package com.example.demo.service;

import com.example.demo.entity.Asset;

public interface AssetService {

    Asset createAsset(
            Asset asset,
            Long vendorId,
            Long depreciationRuleId
    );
}

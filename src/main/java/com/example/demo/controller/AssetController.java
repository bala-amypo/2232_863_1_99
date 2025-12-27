package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.AssetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping
    public Asset create(@RequestBody Asset asset) {

        // TEMPORARY: vendor & rule already set in request JSON
        return assetService.createAsset(
                asset,
                asset.getVendor(),
                asset.getDepreciationRule()
        );
    }
}

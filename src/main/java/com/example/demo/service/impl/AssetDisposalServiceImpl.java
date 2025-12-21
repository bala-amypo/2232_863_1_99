package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetDisposalService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetDisposalRepository disposalRepository;
    private final AssetRepository assetRepository;

    public AssetDisposalServiceImpl(
            AssetDisposalRepository disposalRepository,
            AssetRepository assetRepository) {
        this.disposalRepository = disposalRepository;
        this.assetRepository = assetRepository;
    }

    @Override
    public AssetDisposal disposeAsset(Long assetId, AssetDisposal disposal) {

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        disposal.setAsset(asset);
        disposal.setCreatedAt(LocalDateTime.now());

        asset.setStatus("DISPOSED");
        assetRepository.save(asset);

        return disposalRepository.save(disposal);
    }

    @Override
    public List<AssetDisposal> getAllDisposals() {
        return disposalRepository.findAll();
    }
}

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

    // STEP 1: Dispose asset (request)
    @Override
    public AssetDisposal disposeAsset(Long assetId, AssetDisposal disposal) {

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        disposal.setAsset(asset);
        disposal.setStatus("PENDING");
        disposal.setCreatedAt(LocalDateTime.now());

        asset.setStatus("DISPOSAL_REQUESTED");
        assetRepository.save(asset);

        return disposalRepository.save(disposal);
    }

    // STEP 2: Approve disposal
    @Override
    public AssetDisposal approveDisposal(Long assetId, Long approverId) {

        AssetDisposal disposal = disposalRepository.findByAssetId(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal request not found"));

        disposal.setApprovedBy(approverId);
        disposal.setApprovedAt(LocalDateTime.now());
        disposal.setStatus("APPROVED");

        Asset asset = disposal.getAsset();
        asset.setStatus("DISPOSED");
        assetRepository.save(asset);

        return disposalRepository.save(disposal);
    }

    @Override
    public List<AssetDisposal> getAllDisposals() {
        return disposalRepository.findAll();
    }
}

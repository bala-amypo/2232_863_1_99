package com.example.demo.service;

import com.example.demo.entity.AssetDisposal;

import java.util.List;

public interface AssetDisposalService {

    AssetDisposal disposeAsset(Long assetId, AssetDisposal disposal);

    AssetDisposal approveDisposal(Long assetId, Long approverId);

    List<AssetDisposal> getAllDisposals();
}

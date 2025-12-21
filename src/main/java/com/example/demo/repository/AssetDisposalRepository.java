package com.example.demo.repository;

import com.example.demo.entity.AssetDisposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetDisposalRepository extends JpaRepository<AssetDisposal, Long> {

    Optional<AssetDisposal> findByAssetId(Long assetId);
}

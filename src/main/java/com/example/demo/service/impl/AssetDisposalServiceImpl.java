package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AssetDisposalService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetDisposalRepository disposalRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public AssetDisposalServiceImpl(
            AssetDisposalRepository disposalRepository,
            AssetRepository assetRepository,
            UserRepository userRepository) {

        this.disposalRepository = disposalRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        if (disposal.getDisposalValue() == null || disposal.getDisposalValue() < 0) {
            throw new IllegalArgumentException("Disposal value must be >= 0");
        }

        if (disposal.getDisposalDate() == null ||
                disposal.getDisposalDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Disposal date cannot be in the future");
        }

        if (disposal.getDisposalMethod() == null || disposal.getDisposalMethod().isBlank()) {
            throw new IllegalArgumentException("Disposal method is required");
        }

        // 🔐 Get logged-in user's email from SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        disposal.setAsset(asset);
        disposal.setApprovedBy(currentUser); // ✅ FIX: NOT NULL column satisfied
        // createdAt handled by @PrePersist

        return disposalRepository.save(disposal);
    }


    @Override
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {

        AssetDisposal disposal = disposalRepository.findById(disposalId)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal not found"));

        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin user not found"));

        boolean isAdmin = admin.getRoles().stream()
                .anyMatch(role -> "ADMIN".equals(role.getName()));

        if (!isAdmin) {
            throw new AccessDeniedException("Only ADMIN can approve disposal");
        }

        disposal.setApprovedBy(admin);

        Asset asset = disposal.getAsset();
        asset.setStatus("DISPOSED");
        assetRepository.save(asset);

        return disposalRepository.save(disposal);
    }
}

package com.example.demo.controller;

import com.example.demo.service.AssetDisposalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    private final AssetDisposalService service;

    public AssetDisposalController(
            AssetDisposalService service) {
        this.service = service;
    }

    @PostMapping("/{id}/approve")
    public void approve(@PathVariable Long id) {
        service.approveDisposal(id, 1L);
    }
}

package com.example.demo.controller;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assets/{assetId}/events")
public class AssetLifecycleEventController {

    private final AssetLifecycleEventService service;

    public AssetLifecycleEventController(
            AssetLifecycleEventService service) {
        this.service = service;
    }

    @PostMapping
    public AssetLifecycleEvent log(
            @PathVariable Long assetId,
            @RequestBody AssetLifecycleEvent event) {
        return service.logEvent(assetId, event);
    }
}

package com.example.demo.service;

import com.example.demo.entity.AssetLifecycleEvent;
import java.util.List;

public interface AssetLifecycleEventService {

    List<AssetLifecycleEvent> getAllEvents();
}

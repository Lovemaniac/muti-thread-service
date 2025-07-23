package com.example.mutithreadservice.service.impl;

import com.example.mutithreadservice.service.PricingService;
import org.springframework.stereotype.Service;

@Service
public class PricingServiceImpl implements PricingService {
    @Override
    public Double getPrice(Long productId) {
        simulateDelay();
        return 99.99; // 假设价格
    }

    private void simulateDelay() {
        try { Thread.sleep(100); } catch (InterruptedException ignored) {}
    }
}

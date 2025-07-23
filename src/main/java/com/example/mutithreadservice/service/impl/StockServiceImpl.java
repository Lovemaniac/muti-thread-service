package com.example.mutithreadservice.service.impl;

import com.example.mutithreadservice.service.StockService;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    @Override
    public Integer getStock(Long productId) {
        simulateDelay();
        return 100; // 假设库存
    }

    private void simulateDelay() {
        try { Thread.sleep(150); } catch (InterruptedException ignored) {}
    }
}

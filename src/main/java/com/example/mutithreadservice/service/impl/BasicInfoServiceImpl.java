package com.example.mutithreadservice.service.impl;

import com.example.mutithreadservice.service.BasicInfoService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class BasicInfoServiceImpl implements BasicInfoService {
    public String getBasicInfo(Long productId) {
        // 模拟调用
        simulateDelay();
        return "Basic info for productId: " + productId;
    }

    private void simulateDelay() {
        try { Thread.sleep(200); } catch (InterruptedException ignored) {}
    }

}


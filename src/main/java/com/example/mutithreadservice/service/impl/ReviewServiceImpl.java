package com.example.mutithreadservice.service.impl;

import com.example.mutithreadservice.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Override
    public List<String> getReviews(Long productId) {
        simulateDelay();
        return Arrays.asList("Excellent!", "Value for money", "Fast shipping");
    }

    private void simulateDelay() {
        try { Thread.sleep(180); } catch (InterruptedException ignored) {}
    }
}

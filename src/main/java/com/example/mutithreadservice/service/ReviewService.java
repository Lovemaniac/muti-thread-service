package com.example.mutithreadservice.service;

import java.util.List;

public interface ReviewService {
    public List<String> getReviews(Long productId);
}

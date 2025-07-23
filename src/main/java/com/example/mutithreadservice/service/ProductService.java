package com.example.mutithreadservice.service;

import com.example.mutithreadservice.model.ProductDetails;

public interface ProductService {
    ProductDetails getProductDetails(Long productId);

    ProductDetails getProductDetailsBySerial(Long productId);
}

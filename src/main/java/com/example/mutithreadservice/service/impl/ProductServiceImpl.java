package com.example.mutithreadservice.service.impl;

import com.example.mutithreadservice.model.ProductDetails;
import com.example.mutithreadservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private BasicInfoService basicInfoService;
    @Autowired
    private StockService stockService;
    @Autowired
    private PricingService pricingService;
    @Autowired
    private ReviewService reviewService;

    //创建固定大小的线程池
    private final ExecutorService executor = Executors.newFixedThreadPool(16);

    @Override
    public ProductDetails getProductDetails(Long productId) {
        CompletableFuture<String> basicInfoFuture = CompletableFuture.supplyAsync(() -> basicInfoService.getBasicInfo(productId), executor);
        CompletableFuture<Integer> stockFuture = CompletableFuture.supplyAsync(() -> stockService.getStock(productId), executor);
        CompletableFuture<Double> pricingFuture = CompletableFuture.supplyAsync(() -> pricingService.getPrice(productId), executor);
        CompletableFuture<List<String>> reviewFuture = CompletableFuture.supplyAsync(() -> reviewService.getReviews(productId), executor);

        CompletableFuture<ProductDetails> productDetailsFuture = CompletableFuture.allOf(basicInfoFuture, stockFuture, pricingFuture, reviewFuture)
                .thenApply(v -> {
                    ProductDetails productDetails = new ProductDetails();
                    productDetails.setBasicInfo(basicInfoFuture.join());
                    productDetails.setStock(stockFuture.join());
                    productDetails.setPrice(pricingFuture.join());
                    productDetails.setReviews(reviewFuture.join());
                    return productDetails;
                });
        return productDetailsFuture.join();

    }

    @Override
    public ProductDetails getProductDetailsBySerial(Long productId) {
        String basicInfo = basicInfoService.getBasicInfo(productId);
        Integer stock = stockService.getStock(productId);
        Double price = pricingService.getPrice(productId);
        List<String> reviews = reviewService.getReviews(productId);

        ProductDetails details = new ProductDetails();
        details.setBasicInfo(basicInfo);
        details.setStock(stock);
        details.setPrice(price);
        details.setReviews(reviews);

        return details;
    }


}

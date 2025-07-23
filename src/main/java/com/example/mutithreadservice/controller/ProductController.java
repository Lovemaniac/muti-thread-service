package com.example.mutithreadservice.controller;

import com.example.mutithreadservice.model.ProductDetails;
import com.example.mutithreadservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    //串行执行
    @GetMapping("/details/{productId}")
    public ProductDetails getProductDetails(@PathVariable Long productId){
        return productService.getProductDetails(productId);
    }
    //并行执行
    @GetMapping("/details-serial/{productId}")
    public ProductDetails getProductDetailsSerial(@PathVariable("productId") Long productId) {
        return productService.getProductDetailsBySerial(productId);
    }
}

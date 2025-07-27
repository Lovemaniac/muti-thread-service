package com.example.mutithreadservice.service;

import com.example.mutithreadservice.es.ProductDocument;
import com.example.mutithreadservice.model.Product;
import com.example.mutithreadservice.model.ProductDetails;

public interface ProductService {
    ProductDetails getProductDetails(Long productId);

    ProductDetails getProductDetailsBySerial(Long productId);

    public void addProduct(Product product);

    ProductDocument toDocument(Product p);


}
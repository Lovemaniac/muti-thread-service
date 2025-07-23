package com.example.mutithreadservice.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductDetails {
    private String basicInfo;
    private Integer stock;
    private Double price;
    private List<String> reviews;

    // Getters and setters
}

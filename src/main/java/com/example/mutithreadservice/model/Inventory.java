package com.example.mutithreadservice.model;


import lombok.Data;

import java.util.Date;

@Data
public class Inventory {
    private Long id;             // 库存ID
    private String productId;      // 商品ID
    private Integer stock;       // 库存数量
}
package com.example.mutithreadservice.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {
    private Integer id;
    private String productId;
    private Integer quantity;
    private BigDecimal amount;
    private String status; // NEW, PAID, CANCELLED,
    private Date createTime;
    private Date updateTime;
}

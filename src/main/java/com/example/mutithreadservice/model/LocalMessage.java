package com.example.mutithreadservice.model;

import lombok.Data;

import java.util.Date;

@Data
public class LocalMessage {
    private String messageId;
    private String message;  // JSON字符串
    private String status;   // NEW, SENT, CONFIRMED
    private Date createTime;
    private Date updateTime;
}

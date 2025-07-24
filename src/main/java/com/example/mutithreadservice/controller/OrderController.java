package com.example.mutithreadservice.controller;

import com.example.mutithreadservice.model.Order;
import com.example.mutithreadservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单接口
     * 触发订单创建+本地消息存储+发送库存扣减消息
     */
    @PostMapping
    public String createOrder(@RequestBody Order order) {
        try {
            orderService.createOrder(order);
            return "订单创建成功，已触发库存扣减";
        } catch (Exception e) {
            return "订单创建失败：" + e.getMessage();
        }
    }
}

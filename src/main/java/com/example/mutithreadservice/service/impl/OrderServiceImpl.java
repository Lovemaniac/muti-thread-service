package com.example.mutithreadservice.service.impl;

import com.example.mutithreadservice.mapper.InventoryMapper;
import com.example.mutithreadservice.mapper.LocalMessageMapper;
import com.alibaba.fastjson.JSON;
import com.example.mutithreadservice.mapper.OrderMapper;
import com.example.mutithreadservice.model.LocalMessage;
import com.example.mutithreadservice.model.Order;
import com.example.mutithreadservice.service.InventoryService;
import com.example.mutithreadservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.UUID;



@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private LocalMessageMapper localMessageMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // 注入事务管理器（关键）
    @Autowired
    private PlatformTransactionManager transactionManager;  // 注意类型是接口，而非具体实现
    @Override
    public void createOrder(Order order){
        //手动开启事务
       TransactionStatus tx =transactionManager.getTransaction(new DefaultTransactionDefinition());

       try{
           //1.创建订单
           orderMapper.insert(order);
           //2.记录本地消息
           LocalMessage localMessage = new LocalMessage();
           localMessage.setMessageId(UUID.randomUUID().toString());
           localMessage.setMessage(JSON.toJSONString(order));
           localMessage.setStatus("NEW");
           localMessageMapper.insert(localMessage);
           //3.提交事务
           transactionManager.commit(tx);
           //4.发送消息到kafka（不要在事务中发送）
           kafkaTemplate.send("inventory-topic",localMessage.getMessageId(),localMessage.getMessage());

       }catch (Exception e){
               transactionManager.rollback(tx);
           throw new RuntimeException("创建订单失败",e);
       }
    }


}







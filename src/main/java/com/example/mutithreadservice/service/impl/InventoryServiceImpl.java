package com.example.mutithreadservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.mutithreadservice.mapper.InventoryMapper;
import com.example.mutithreadservice.model.Order;
import com.example.mutithreadservice.service.InventoryService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    @KafkaListener(topics = "inventory-topic")
    public void handleOrderCreation(ConsumerRecord<String, String> record) {
        String messageId = record.key();
        Order order = JSON.parseObject(record.value(), Order.class);

        try {
            // 执行库存扣减逻辑
            inventoryMapper.decreaseStock(order.getProductId(), order.getQuantity());
            // 确认消息处理成功
            kafkaTemplate.send("inventory-result-topic", messageId, "SUCCESS");
        } catch (Exception e) {
            // 确认消息处理失败
            kafkaTemplate.send("inventory-result-topic", messageId, "FAILED");
        }

    }
}

package com.example.mutithreadservice.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface InventoryService {
    void handleOrderCreation(ConsumerRecord<String, String> record);
}

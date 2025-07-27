package com.example.mutithreadservice.kafka;

import com.alibaba.fastjson.JSON;
import com.example.mutithreadservice.es.ProductDocument;
import com.example.mutithreadservice.repository.ESProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    @Autowired
     private ESProductRepository esProductRepository;


    @KafkaListener(topics = "product-topic", groupId = "product-group")
    public void consume(String message) {
        try {
            ProductDocument document = JSON.parseObject(message, ProductDocument.class);
            esProductRepository.save(document);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

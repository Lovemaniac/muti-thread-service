package com.example.mutithreadservice.kafka;

import com.alibaba.fastjson.JSON;
import com.example.mutithreadservice.es.ProductDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(ProductDocument doc) {
        kafkaTemplate.send("product-topic", JSON.toJSONString(doc));
    }
}


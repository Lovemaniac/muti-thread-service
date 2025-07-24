package com.example.mutithreadservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // 启用定时任务
@EnableKafka       // 启用Kafka监听
public class MutiThreadServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MutiThreadServiceApplication.class, args);
    }

}

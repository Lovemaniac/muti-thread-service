package com.example.mutithreadservice.component;

import com.example.mutithreadservice.mapper.LocalMessageMapper;
import com.example.mutithreadservice.model.LocalMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

//消息重传系统
@Component
@Slf4j
public class MessageRecoverySystem {
    @Autowired
    private LocalMessageMapper localMessageMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 6000)
    public void recoverMessages() {
        // 计算5分钟前的时间
        Date fiveMinutesAgo = new Date(System.currentTimeMillis() - 300000);

        // 1. 重试发送失败（NEW）和处理失败（FAILED）的消息
        List<LocalMessage> retryMessages = localMessageMapper.findByStatusInAndCreateTimeBefore(
                Arrays.asList("NEW", "FAILED"),  // 同时处理两种状态
                fiveMinutesAgo
        );
        for (LocalMessage message : retryMessages) {
            try {
                kafkaTemplate.send("inventory-topic", message.getMessageId(), message.getMessage());
                // 发送成功后，状态改为SENT（等待库存服务再次处理）
                message.setStatus("SENT");
                localMessageMapper.updateStatus(message);
            } catch (Exception e) {
                // 记录日志,等待下次重试
                log.error("Failed to recover message: " + message.getMessageId(), e);
            }
        }
    }


    @KafkaListener(topics = "inventory-result-topic")
    public void handleInventoryResult(ConsumerRecord<String, String> record) {
        String messageId = record.key();
        String result = record.value();
        LocalMessage message = localMessageMapper.selectById(messageId);
        if(message != null){
            if("SUCCESS".equals(result)){
                // 更新本地消息状态为SUCCESS
                message.setStatus("SUCCESS");
            }else{
                // 更新本地消息状态为FAILED
                message.setStatus("FAILED");
            }
            localMessageMapper.updateStatus(message);

        }

    }

}

package com.example.mutithreadservice.mapper;

import com.example.mutithreadservice.model.LocalMessage;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface LocalMessageMapper {

    /**
     * 保存本地消息
     */
    @Insert("INSERT INTO local_message (message_id, message, status, create_time, update_time) " +
            "VALUES (#{messageId}, #{message}, #{status}, NOW(), NOW())")
    void insert(LocalMessage message);

    /**
     * 根据消息ID查询
     */
    @Select("SELECT * FROM local_message WHERE message_id = #{messageId}")
    LocalMessage selectById(String messageId);

    /**
     * 查询状态为NEW和failed且创建时间超过指定时间的消息（用于消息恢复）
     */
    // 按状态列表和时间查询消息
    @Select("SELECT * FROM local_message WHERE status IN " +
            "<foreach collection='statusList' item='status' open='(' separator=',' close=')'>" +
            "#{status}" +
            "</foreach>" +
            " AND create_time < #{time}")
    List<LocalMessage> findByStatusInAndCreateTimeBefore(
            @Param("statusList") List<String> statusList,
            @Param("time") Date time
    );

    /**
     * 更新消息状态
     */
    @Update("UPDATE local_message SET status = #{status}, update_time = NOW() WHERE message_id = #{messageId}")
    void updateStatus(LocalMessage message);
}

package com.example.mutithreadservice.mapper;

import com.example.mutithreadservice.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface OrderMapper {

    /**
     * 保存订单（自增ID需在XML中配置useGeneratedKeys）
     */
    @Insert("INSERT INTO t_order (product_id, quantity, amount, status) " +
            "VALUES (#{productId}, #{quantity}, #{amount}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 自动生成订单ID
    void insert(Order order);
}

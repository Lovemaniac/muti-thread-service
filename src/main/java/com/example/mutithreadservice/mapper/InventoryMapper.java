package com.example.mutithreadservice.mapper;

import com.example.mutithreadservice.model.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface InventoryMapper {

    /**
     * 根据商品ID查询库存
     */
    @Select("SELECT * FROM inventory WHERE product_id = #{productId}")
    Inventory selectByProductId(String productId);

    /**
     * 扣减库存（注意：此处需保证原子性，可加行锁）
     */
    @Update("UPDATE inventory SET stock = stock - #{quantity} WHERE product_id = #{productId} AND stock >= #{quantity}")
    int decreaseStock(String productId, Integer quantity);
    // 返回值为影响行数，0表示扣减失败（库存不足）
}

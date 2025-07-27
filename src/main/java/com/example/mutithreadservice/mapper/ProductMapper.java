package com.example.mutithreadservice.mapper;

import com.example.mutithreadservice.model.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductMapper {
    @Insert("insert into product(id,name,brand,description,price) values(#{id},#{name},#{brand},#{description},#{price})")
    void insert(Product product);

    @Update("update product set name=#{name},price=#{price} where id=#{id}")
    void update(Product product);

    @Select("select * from product where id=#{id}")
    Product selectById(String id);
}

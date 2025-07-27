package com.example.mutithreadservice.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "products", createIndex = true)  // 关闭自动创建索引
public class ProductDocument{
    @Id
    @Field(type = FieldType.Keyword)  // 与映射一致
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Keyword)
    private String brand;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Float)
    private Double price;  // 或 Float，与映射一致

    @Field(
            type = FieldType.Date,
            format = {DateFormat.date_hour_minute_second})
    private LocalDateTime createTime;


}

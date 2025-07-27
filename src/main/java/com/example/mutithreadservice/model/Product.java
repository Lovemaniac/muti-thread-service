package com.example.mutithreadservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Product {
    private String id;

    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createTime;

    public Double getPrice(){
        if(price != null) {
            return price.doubleValue();
        }else {
            return null;
        }
    }

}

// User.java - 实体类
package com.example.mutithreadservice.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String email;
    private String role;
}


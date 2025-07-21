package com.example.mutithreadservice.controller;

import com.example.mutithreadservice.model.User;
import com.example.mutithreadservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 静态常量，仅初始化一次
    private static final List<Integer> ids;

    static {
        ids = new ArrayList<>(1000);
        for (int i = 1; i <= 1000; i++) {
            ids.add(i);
        }
    }


    //模拟多个请求
    @GetMapping("/Multiple-requests")
    public List<User> getMultipleUsers() {
       return userService.getMultipleUsers(ids);
    }


@GetMapping("/request")
    public List<User> getUsers() {
        List<User> result = new ArrayList<>();
        for (Integer id : ids) {
            User entity = userService.selectById(id);
            if (entity != null) {
                result.add(entity);
            }
        }
        return result;
    }



}


package com.example.mutithreadservice.service.impl;


import com.example.mutithreadservice.mapper.UserMapper;
import com.example.mutithreadservice.model.User;
import com.example.mutithreadservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //创建固定大小的线程池
    private final ExecutorService executor = Executors.newFixedThreadPool(16);

    @Override
    public List<User> getMultipleUsers(List<Integer> ids) {
        // 为每个ID创建异步任务
        List<CompletableFuture<User>> futures = ids.stream()
                .map(id -> CompletableFuture.supplyAsync(() -> userMapper.getUserById(id), executor))
                .collect(Collectors.toList());

        // 等待所有任务完成并收集结果
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        //收集所有结果
        ArrayList<User> result = new ArrayList<>();
        for (CompletableFuture<User> future : futures) {
           User user= future.join();
           if(user!=null){
               result.add(user);
           }
        }
        return result;
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.getUserById(id);
    }

    // 关闭线程池
    @PreDestroy
    public void shutdown() {
        executor.shutdown();
    }


}
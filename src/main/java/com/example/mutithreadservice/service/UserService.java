package com.example.mutithreadservice.service;


    import com.example.mutithreadservice.model.User;

    import java.util.ArrayList;
    import java.util.List;

    public interface UserService {


        List<User> getMultipleUsers(List<Integer> ids);

        User selectById(Integer id);
    }


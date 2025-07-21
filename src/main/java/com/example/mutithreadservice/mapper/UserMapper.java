// UserMapper.java - MyBatis Mapper接口
package com.example.mutithreadservice.mapper;

import com.example.mutithreadservice.model.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {

    // Select a user from the database by their id
    @Select("SELECT * FROM users WHERE id = #{id}")
    User getUserById(Integer id);

    // Select all users from the database
    @Select("SELECT * FROM users")
    List<User> findAll();

    // Insert a new user into the database
    @Insert("INSERT INTO users(username, email, role) VALUES(#{username}, #{email}, #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    // Update an existing user in the database
    @Update("UPDATE users SET username=#{username}, email=#{email}, role=#{role} WHERE id=#{id}")
    int update(User user);

    // Delete a user from the database by their id
    @Delete("DELETE FROM users WHERE id=#{id}")
    int delete(Long id);


}
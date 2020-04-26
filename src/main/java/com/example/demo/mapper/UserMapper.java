package com.example.demo.mapper;

import com.example.demo.entity.Permisson;
import com.example.demo.entity.ShiroUser;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/1 14:30
 */
@Mapper
public interface UserMapper {

     User getUser(@Param("user")  User user);
}




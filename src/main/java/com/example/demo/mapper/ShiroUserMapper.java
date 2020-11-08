package com.example.demo.mapper;

import com.example.demo.entity.ShiroUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/1 14:30
 */
@Mapper
public interface ShiroUserMapper {
    ShiroUser getUser(@Param("name") String name, @Param("password") String password);

    List<ShiroUser> getUserPage(@Param("name") String name, @Param("start") Integer start, @Param("size") Integer size);

    Integer getTotal(@Param("name") String name, @Param("start") Integer start, @Param("size") Integer size);
}




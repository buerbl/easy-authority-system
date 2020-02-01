package com.example.demo.mapper;

import com.example.demo.entity.ShiroUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/1 14:30
 */
@Mapper
public interface ShiroUserMapper {
     ShiroUser getUser(@Param("name") String name, @Param("password") String password);
}




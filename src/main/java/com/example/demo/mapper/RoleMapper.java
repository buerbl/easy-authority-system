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
public interface RoleMapper {
    List<String> getRole(String name);
}




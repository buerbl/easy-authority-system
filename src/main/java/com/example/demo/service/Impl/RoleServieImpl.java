package com.example.demo.service.Impl;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserService;
import com.example.demo.util.StatusEnum;
import com.example.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/1 14:34
 */
@Service
@Slf4j
public class RoleServieImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public String getRole(String name) {
        log.info("sdasa:{}",roleMapper.getRole(name).toString());
        List<String> roles = roleMapper.getRole(name);
        if (!roles.isEmpty()){
            return roles.stream().filter(s -> s.equals("admin")).collect(Collectors.toList()).isEmpty()
                    ?roles.get(0)
                    :roles.stream().filter(s -> s.equals("admin")).collect(Collectors.toList()).get(0);
        }else {
            return "没有角色";
        }
    }



}




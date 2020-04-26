package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.Permisson;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/1 14:34
 */
@Service
@Slf4j
public class UserServieImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    public User getUser(User user) {
        User origin = userMapper.getUser(user);
        for (Role role : origin.getRoleList()){
           log.info(JSON.toJSONString(role.getPermissonList()));
            if (role.getRoleName().equals("admin")){
                handle(role);
                break;
            }
            handle(role);

        }
        return userMapper.getUser(user);
    }

    private void handle(Role role) {
        for (Permisson permisson : role.getPermissonList()){

        }
    }
}




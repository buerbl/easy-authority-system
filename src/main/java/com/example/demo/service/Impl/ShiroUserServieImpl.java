package com.example.demo.service.Impl;

import com.example.demo.entity.ShiroUser;
import com.example.demo.mapper.ShiroUserMapper;
import com.example.demo.service.IShiroUserService;
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
public class ShiroUserServieImpl implements IShiroUserService {
    @Autowired
    private ShiroUserMapper shiroUserMapper;
    @Override
    public ShiroUser getUser(String name, String password) {
        log.info("name为{}, password为{}", name, password);
        return shiroUserMapper.getUser(name, password);
    }
}




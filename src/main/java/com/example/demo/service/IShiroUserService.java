package com.example.demo.service;

import com.example.demo.entity.ShiroUser;

public interface IShiroUserService {
    /**
     * 登录方法
     * @param name
     * @param password
     * @return
     */
    ShiroUser getUser(String name, String password);
}

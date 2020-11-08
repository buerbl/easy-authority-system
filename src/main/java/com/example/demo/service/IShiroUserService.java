package com.example.demo.service;

import com.example.demo.dto.ShiroUserDto;
import com.example.demo.entity.ShiroUser;
import com.example.demo.vo.ShiroUserVo;

public interface IShiroUserService {
    /**
     * 登录方法
     *
     * @param name
     * @param password
     * @return
     */
    ShiroUser getUser(String name, String password);

    /**
     * 分页查询
     *
     * @param dto
     * @return
     */
    ShiroUserVo getUserPage(ShiroUserDto dto);
}

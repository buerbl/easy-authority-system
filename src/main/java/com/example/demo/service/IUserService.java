package com.example.demo.service;

import com.example.demo.dto.ShiroUserDto;
import com.example.demo.entity.ShiroUser;
import com.example.demo.entity.User;
import com.example.demo.vo.ShiroUserVo;

public interface IUserService {
    User getUser(User user);
}

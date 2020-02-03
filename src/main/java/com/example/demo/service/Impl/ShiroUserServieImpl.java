package com.example.demo.service.Impl;

import com.example.demo.dto.ShiroUserDto;
import com.example.demo.entity.ShiroUser;
import com.example.demo.vo.ShiroUserVo;
import com.example.demo.mapper.ShiroUserMapper;
import com.example.demo.service.IShiroUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Override
    public ShiroUserVo getUserPage(ShiroUserDto dto) {
        String name = Optional.ofNullable(dto.getShiroUser()).map(ShiroUser::getName).orElse(null);
        Integer start = ( dto.getPagenum() - 1 ) * dto.getSize();
        Integer size = dto.getSize();
        List<ShiroUser> shiroUserList = shiroUserMapper.getUserPage(name, start, size);
        Integer total = shiroUserMapper.getTotal(name, start, size);
        ShiroUserVo shiroUserVo = new ShiroUserVo();
        shiroUserVo.setShiroUserList(shiroUserList);
        shiroUserVo.setTotal(total);
        return shiroUserVo;
    }
}




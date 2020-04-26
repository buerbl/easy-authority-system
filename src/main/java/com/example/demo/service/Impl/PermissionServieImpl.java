package com.example.demo.service.Impl;

import com.example.demo.entity.Permisson;
import com.example.demo.entity.Role;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/1 14:34
 */
@Service
@Slf4j
public class PermissionServieImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    public Role getPermisson(Permisson permission) {
        return permissionMapper.getPermission(permission);
    }

    @Override
    public List<Permisson> getTree() {
        return permissionMapper.getTree(0);
    }
}




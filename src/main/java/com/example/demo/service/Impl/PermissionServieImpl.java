package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Permisson;
import com.example.demo.entity.Role;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.service.IPermissionService;
import com.example.demo.vo.PermissionVO;
import com.example.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    @Override
    public UserVo getPermisson(String roleName) {
        if (Objects.isNull(roleName)){
            return null;
        }
        List<PermissionVO> permissons = permissionMapper.getPermissionByroleName(roleName);
        List<PermissionVO> result = new ArrayList<PermissionVO>();

        // 1、获取第一级节点
        for (PermissionVO permisson : permissons) {
            if(0 == permisson.getPpid()) {
                result.add(permisson);
            }
        }

        // 2、递归获取子节点
        for (PermissionVO parent : result) {
            parent = recursiveTree(parent, permissons);
        }
        log.info("结果：{}", JSONObject.toJSON(result));
//        return result;

        return null;
    }

    private PermissionVO recursiveTree(PermissionVO parent, List<PermissionVO> list) {
        for (PermissionVO menu : list) {
            if(Objects.equals(parent.getPid(),menu.getPpid())) {
                menu = recursiveTree(menu, list);
                parent.getChildren().add(menu);
            }
        }
        return parent;
    }
}




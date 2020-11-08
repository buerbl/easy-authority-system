package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Permisson;
import com.example.demo.entity.Role;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.service.IPermissionService;
import com.example.demo.vo.PermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/1 14:34
 */
@Service
@Slf4j
public class PermissionServiceImpl implements IPermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    public Role getPermisson(Permisson permission) {
        return permissionMapper.getPermission(permission);
    }

    @Override
    public List<Permisson> getTree() {
        return permissionMapper.getTree(0);
    }

    @Override
    public List<PermissionVO> getPermisson(String roleName) {
        if (Objects.isNull(roleName)){
            return Collections.emptyList();
        }
        List<PermissionVO> permissons = permissionMapper.getPermissionByroleName(roleName);
        List<PermissionVO> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(permissons)) {
            throw new RuntimeException("没有权限");
        }
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
        List<String> buttonPermissin = getButtonPermissin(permissons);
        if (!CollectionUtils.isEmpty(result)) {
            result.get(0).setButtonPermissin(buttonPermissin);
        }
        log.info("树形结果：{}", JSONObject.toJSON(result));
        return result;
    }

    @Override
    public List<PermissionVO> getPermissionList(String roleName) {
        return permissionMapper.getPermissionByroleName(roleName);
    }

    private List<String> getButtonPermissin(List<PermissionVO> permissons) {
        List<String> buttonPermissins = new ArrayList<>();
        if (permissons.isEmpty()){
            return Collections.emptyList();
        }
        permissons.forEach(a->{
            String typePath = a.getTypePath();
            if (!StringUtils.isEmpty(typePath)){
                buttonPermissins.add(typePath);
            }
        });
        return buttonPermissins;
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




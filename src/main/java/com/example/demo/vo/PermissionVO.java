package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 布尔bl
 * @create 2020/4/28 18:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionVO {
    private Integer rid; // 角色id
    private String roleName; // 角色类型
    private Integer rpId; // 角色权限id
    private Integer pid; //权限id
    private Integer ppid; // 权限的父节点
    private String typeName; // 权限名称
    private String typePath; // 权限路径

    public  List<PermissionVO> children = new ArrayList<PermissionVO>();;
}

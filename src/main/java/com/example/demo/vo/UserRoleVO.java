package com.example.demo.vo;

import lombok.Data;

/**
 * @Author 布尔bl
 * @create 2020/11/15 20:54
 */
@Data
public class UserRoleVO {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;


    /**
     * 角色名称
     */
    private String roleName;
}

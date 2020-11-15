package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author 布尔bl
 * @create 2020/4/28 18:50
 */
@Data
@AllArgsConstructor
public class LoginVO {
    private List<PermissionVO> permissionVOS;
    private String session;
    private String username;
}

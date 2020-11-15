package com.example.demo.vo;

import com.example.demo.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @Author 布尔bl
 * @create 2020/11/15 21:03
 */
@Data
public class UserRoleVoPage {
    private List<UserRoleVO> userList;
    private Integer total;
}

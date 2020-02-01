package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/1 14:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiroUser {
    private Integer id;
    private String name;
    private String password;
    private String role;

}




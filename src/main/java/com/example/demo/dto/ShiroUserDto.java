package com.example.demo.dto;

import com.example.demo.entity.ShiroUser;
import lombok.Data;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/3 15:15
 */
@Data
public class ShiroUserDto {
    private ShiroUser shiroUser;
    private Integer total;
    private Integer pagenum=1;
    private Integer size=10;
}




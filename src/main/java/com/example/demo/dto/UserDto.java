package com.example.demo.dto;

import com.example.demo.entity.ShiroUser;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/3 15:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private User user;
    private Integer total;
    private Integer pagenum=1;
    private Integer size=10;
}




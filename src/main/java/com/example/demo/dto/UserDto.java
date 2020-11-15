package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/3 15:15
 */
@Data
public class UserDto implements Serializable {
    private User user;
    private Integer total;
    private Integer pagenum=1;
    private Integer size=10;
}




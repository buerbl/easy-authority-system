package com.example.demo.entity;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author 布尔bl
 * @create 2020/4/26 18:06
 */
@Data
public class User {
//    @NotNull
    private Integer id;
    @NotNull
    private String name;
    private String password;
    private String adress;

    private Integer sex;

    private String sexStr;

    private Integer status;
    private Boolean statuFlag;

    private List<Role> roleList;
}

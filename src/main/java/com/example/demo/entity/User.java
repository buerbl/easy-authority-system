package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author 布尔bl
 * @create 2020/4/26 18:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    private String password;
    private String adress;

    private Integer status;
    private Boolean statuFlag;

    private List<Role> roleList;
}

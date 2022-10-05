package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @Author 布尔bl
 * @create 2020/4/26 18:06
 */
@Data
public class User {
//    @NotNull
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String name;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "地址不能为空")
    private String adress;

    private Integer sex;

    private String sexStr;

    private Integer status;
    private Boolean statuFlag;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date ctime;

    private List<Role> roleList;
}

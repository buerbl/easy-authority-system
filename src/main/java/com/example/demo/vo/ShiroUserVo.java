package com.example.demo.vo;

import com.example.demo.entity.ShiroUser;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/3 15:11
 */
@Data
public class ShiroUserVo {
    private List<ShiroUser> shiroUserList;
    private Integer total;

}




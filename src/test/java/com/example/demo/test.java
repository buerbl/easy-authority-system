package com.example.demo;

import com.example.demo.dto.ShiroUserDto;
import com.example.demo.entity.ShiroUser;
import org.junit.Test;

import java.util.Optional;


/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/3 16:08
 */
public class test {
    @Test
    public void test(){
        ShiroUserDto dto = new ShiroUserDto();
        dto.setShiroUser(new ShiroUser());
        dto.getShiroUser().setName("ss");
        String name = Optional.ofNullable(dto.getShiroUser()).map(ShiroUser::getName).orElse("未知");
        System.out.println(name);
    }
}




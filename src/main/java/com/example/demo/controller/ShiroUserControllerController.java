package com.example.demo.controller;

import com.example.demo.dto.ShiroUserDto;
import com.example.demo.service.IShiroUserService;
import com.example.demo.util.BaseResult;
import com.example.demo.util.Code;
import com.example.demo.util.Result;
import com.example.demo.vo.ShiroUserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 布尔bl
 * @create 2020/4/25 18:00
 */
@Slf4j
@RestController
public class ShiroUserControllerController extends BaseResult {
    @Autowired
    private IShiroUserService shiroUserService;

    @PostMapping("/getUserPage")
    public Result getUserPage(@RequestBody ShiroUserDto dto){
        log.info("dto为：{}", dto.toString());
        ShiroUserVo shiroUserVo = shiroUserService.getUserPage(dto);
        return getResult(shiroUserVo, Code.SUCCESS.getCode());
    }

    //    @RequiresRoles({"admin"})
    @RequiresPermissions("add")
    @GetMapping("/test")
    public String test() {
        log.info("通过了接口权限验证");
        return "通过了接口权限验证";
    }
}

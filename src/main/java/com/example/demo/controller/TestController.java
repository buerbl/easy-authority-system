package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/1/31 21:48
 */
@Controller
@Slf4j
public class TestController {
    @GetMapping("/test")
    public String test(Model model){
        log.info("测试thymeleaf");
        // 那数据放进 model
        model.addAttribute("name", "布尔bl");
        return "hello";
    }

    @GetMapping("/add")
    public String add(){
        log.info("测试增加用户");
        return "/user/add";
    }

    @GetMapping("/update")
    public String update(){
        log.info("测试更新用户");
        return "/user/update";
    }

    @GetMapping("/tologin")
    public String tologin(){
        log.info("测试拦截");
        return "login";
    }

    @GetMapping("/noauto")
    public String noauto(){
        log.info("测试授权拦截");
        return "noauto";
    }

    @PostMapping("/login")
    public String login(String name, String password, Model model){
        log.info("登录");
        log.info(name+"+"+password);
        // 1. 获取 Subject
        Subject subject  = SecurityUtils.getSubject();

        // 2. 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        try {
            subject.login(token);

        } catch (UnknownAccountException e){
            model.addAttribute("msg", "用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg", "密码错误");
            return "login";
        }
        return "hello";
    }
}




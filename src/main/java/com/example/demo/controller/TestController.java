package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}




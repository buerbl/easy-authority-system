package com.example.demo.controller;

import com.example.demo.dto.ShiroUserDto;
import com.example.demo.entity.ShiroUser;
import com.example.demo.service.IShiroUserService;
import com.example.demo.util.BaseResult;
import com.example.demo.util.Code;
import com.example.demo.util.Result;
import com.example.demo.vo.ShiroUserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/1/31 21:48
 */
@RestController
@Slf4j
public class ShiroUserController extends BaseResult {

    @Autowired
    private IShiroUserService shiroUserService;
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
    public Result tologin(){
        log.info("测试拦截");
        return getResult(Code.ERROR.getMsg(), Code.ERROR.getCode());
    }

    @GetMapping("/noauto")
    public String noauto(){
        log.info("测试授权拦截");
        return "noauto";
    }

    @PostMapping("/login")
    public Result login(@RequestBody ShiroUser dto){
        log.info("登录");
        log.info(dto.getName()+"+"+dto.getPassword());
        // 1. 获取 Subject
        Subject subject  = SecurityUtils.getSubject();
        Session session = subject.getSession();
        // 2. 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(dto.getName(), dto.getPassword());
        try {
            subject.login(token);

        } catch (UnknownAccountException e){
            log.info("msg", "用户名不存在");
            return getResult("用户名不存在", Code.USERNAMEERROR.getCode());
        }catch (IncorrectCredentialsException e){
            log.info( "密码错误");
            return getResult("密码错误", Code.PASSWORDERROR.getCode());
        }
        return getResult(session.getId().toString(), Code.SUCCESS.getCode());
    }

    @PostMapping("/app")
    public Result app(HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info("session为:{}", session.getId());
        return getResult(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
    }

    @GetMapping
    public Result getShiroUser(@RequestBody ShiroUserDto dto){
        log.info("dto为：{}", dto.toString());
        ShiroUserVo shiroUserVo = shiroUserService.getUserPage(dto);
        return getResult(shiroUserVo, Code.SUCCESS.getCode());
    }

}




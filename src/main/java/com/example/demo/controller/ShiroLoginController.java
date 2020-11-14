package com.example.demo.controller;

import com.example.demo.entity.ShiroUser;
import com.example.demo.service.IPermissionService;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IShiroUserService;
import com.example.demo.util.BaseResult;
import com.example.demo.util.Code;
import com.example.demo.util.Result;
import com.example.demo.vo.LoginVO;
import com.example.demo.vo.PermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/1/31 21:48
 */
@RestController
@Slf4j
public class ShiroLoginController extends BaseResult {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/tologin")
    public Result tologin() {
        log.info("没有登录");
        return getResult(Code.NOSIGNIN.getMsg(), Code.NOSIGNIN.getCode());
    }

    @GetMapping("/noauto")
    public Result noauto() {
        log.info("没有授权");
        return getResult(Code.NORIGHT.getMsg(), Code.NORIGHT.getCode());
    }

    @PostMapping("/login")
    public Result login(@RequestBody ShiroUser dto) {
        log.info("登录开始");
        log.info("用户名为[{}], 密码为[{}]", dto.getName(), dto.getPassword());
        // 1. 获取 Subject
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        // 2. 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(dto.getName(), dto.getPassword());
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            log.error("用户名[{}]不存在", dto.getName());
            return getResult("用户名不存在", Code.USERNAMEERROR.getCode());
        } catch (IncorrectCredentialsException e) {
            log.error("密码错误");
            return getResult("密码错误", Code.PASSWORDERROR.getCode());
        }
        log.info("我的凭证:{}", session.getId().toString());
        String role = roleService.getRole(dto.getName());
        List<PermissionVO> permissonVOS = permissionService.getPermisson(role);
        log.info("登录结束");
        return getResult(new LoginVO(permissonVOS, session.getId().toString()), Code.SUCCESS.getCode());
    }

    @GetMapping("/logout")
    public Result logout() {
        log.info("登出");
        return getResult("退出", Code.SUCCESS.getCode());
    }
}




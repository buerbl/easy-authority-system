package com.example.demo.controller;

import cn.hutool.core.date.StopWatch;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.ShiroUser;
import com.example.demo.entity.User;
import com.example.demo.service.IPermissionService;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserService;
import com.example.demo.util.*;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;


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
    @Autowired
    private IUserService userService;
    @Resource
    HttpServletRequest req;

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
        log.info("登录开始了");
        String username = dto.getName();
        log.info("用户名为[{}], 密码为[{}]", username, dto.getPassword());
        try {
            String ip = GetIP.getIP(req);
            getIp(ip);
        }catch (Exception e){

        }

        // 1. 获取 Subject
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        // 2. 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, dto.getPassword());
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            log.error("用户名[{}]不存在", username);
            return getResult("用户名不存在", Code.USERNAMEERROR.getCode());
        } catch (IncorrectCredentialsException e) {
            log.error("密码错误");
            return getResult("密码错误", Code.PASSWORDERROR.getCode());
        }
        User userByName = userService.getUserByName(username);
        if (Objects.equals(userByName.getStatus(), 0)){
            throw new RuntimeException("用户已经停用");
        }
        log.info("我的凭证:{}", session.getId().toString());
        String role = roleService.getRole(username);
        List<PermissionVO> permissonVOS = permissionService.getPermisson(role);
        log.info("登录结束了");
        return getResult(new LoginVO(permissonVOS, session.getId().toString(), username), Code.SUCCESS.getCode());
    }

    private void getIp(String ip) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("获取ip时间");
        String post = HttpUtil.get("https://opendata.baidu.com/api.php?query="+ip+"&co=&resource_id=6006&oe=utf8");
        JSONObject jsonObject  = JSON.parseObject(post);
        stopWatch.stop();
        log.info("获取ip时间为{}", stopWatch.getTotalTimeSeconds());
        JSONArray result = (JSONArray) jsonObject.get("data");
        JSONObject data = (JSONObject) result.get(0);
        String location = data.getString("location");

        userService.addIpp(ip, location);
    }


    @GetMapping("/logout")
    public Result logout() {
        log.info("登出");
        return getResult("退出", Code.SUCCESS.getCode());
    }
}




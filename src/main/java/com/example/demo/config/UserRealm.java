package com.example.demo.config;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.Permisson;
import com.example.demo.entity.Role;
import com.example.demo.entity.ShiroUser;
import com.example.demo.entity.User;
import com.example.demo.service.IShiroUserService;
import com.example.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/1/31 22:09
 * 自定义的Realm
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    /**
     * 执行授权逻辑
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("执行授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        User userInfo = userService.getUserInfo(user);
        log.info("要授权的userInfo：{}", JSON.toJSON(userInfo));

        for (Role role : userInfo.getRoleList()) {
            if (role.getRoleName().equals("admin")) {
                for (Permisson permisson : role.getPermissonList()) {
                    for (Permisson chil : permisson.getChildren()) {
                        for (Permisson chil1 : chil.getChildren()) {
                            info.addStringPermission(chil1.getTypePath());
                        }

                    }
                }
                break;
            }
            for (Permisson permisson : role.getPermissonList()) {
                for (Permisson chil : permisson.getChildren()) {
                    for (Permisson chil1 : chil.getChildren()) {
                        info.addStringPermission(chil1.getTypePath());
                    }

                }
            }
        }
        log.info("获得授权的info：{}", JSON.toJSON(info));
        return info;
    }


    /**
     * 执行认证逻辑
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("执行认证逻辑");

        UsernamePasswordToken token1 = (UsernamePasswordToken) token;
        User user = userService.getUser(token1.getUsername(), String.copyValueOf(token1.getPassword()));
        // 判断用户名
        if (user == null) {
            return null; //shiro 抛出 UnaKnowAccountException
        }

        // 判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}




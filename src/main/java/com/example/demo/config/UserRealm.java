package com.example.demo.config;

import com.example.demo.entity.ShiroUser;
import com.example.demo.service.IShiroUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/1/31 22:09
 * 自定义的Realm
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    /**
     * 执行授权逻辑
      * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("执行授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("add");
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        info.addStringPermission(user.getRole());
        return info;
    }


    @Autowired
    private IShiroUserService shiroUserService;
    /**
     * 执行认证逻辑
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("执行认证逻辑");

        UsernamePasswordToken token1 = (UsernamePasswordToken) token;
        ShiroUser user = shiroUserService.getUser(token1.getUsername(), String.copyValueOf(token1.getPassword()));
        // 判断用户名
        if (user==null){
            return null; //shiro 抛出 UnaKnowAccountException
        }

        // 判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}




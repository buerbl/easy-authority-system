package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

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
        return null;
    }

    /**
     * 执行认证逻辑
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("执行认证逻辑");
        String name = "root";
        String password = "123";

        UsernamePasswordToken token1 = (UsernamePasswordToken) token;

        // 判断用户名
        if (!token1.getUsername().equals(name)){
            return null; //shiro 抛出 UnaKnowAccountException
        }

        // 判断密码
        return new SimpleAuthenticationInfo("", password, "");
    }
}




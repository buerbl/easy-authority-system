package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/1/31 22:09
 */
@Configuration
public class ShrioConfig {


    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean( DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();


        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加 shiro 内置过滤器
        /**
         * Shiro 内置过滤器，可以实现权限相关的拦截器
         *  常用的过滤器
         *     anon: 无需认证（登录）可以访问
         *     authc: 必须认证才可以访问
         *     user：如果使用 remenmberMe 的功能可以直接访问
         *     perms： 该资源必须得到资源权限才可以访问
         *     role： 该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/test", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/logout", "anon");
//        filterMap.put("/test","perms[add]");
//        filterMap.put("/update","perms[update]");
        // 拦截所有
        filterMap.put("/*", "authc");

        shiroFilterFactoryBean.setLoginUrl("/tologin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauto");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


        return shiroFilterFactoryBean;
    }


    /**
     * 自定义sessionManager
     * @return
     */
    @Bean
    public SessionManager sessionManager(){
        ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
        return shiroSessionManager;
    }
    /**
     * 创建 DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager( UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联realm
        securityManager.setRealm(userRealm);
        //自定义session管理
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 创建 Realm
     */
    @Bean
    public UserRealm getRealm(){
        return new UserRealm();
    }

    // 开启注解
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
                = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}




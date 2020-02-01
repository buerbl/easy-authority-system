package com.example.demo.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
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
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager){
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
        filterMap.put("/test", "anon");
        filterMap.put("/login", "anon");

        filterMap.put("/add","perms[add]");
        filterMap.put("/update","perms[update]");
        // 拦截所有
        filterMap.put("/*", "authc");



        shiroFilterFactoryBean.setLoginUrl("/tologin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauto");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }



    /**
     * 创建 DefaultWebSecurityManager
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建 Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }


}




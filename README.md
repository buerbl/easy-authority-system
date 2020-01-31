# easy-authority-system

## 分析 Shrio 的核心 API

Subject : 用户主体（把操作交给 SecurityManager）
SecurityManager : 安全管理器（关联 Realm）
Realm ：Shiro 连接数据的桥梁

## shiro 引入

```java
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-spring</artifactId>
    <version>1.4.0</version>
</dependency>
```

## 简单拦截

```java
public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
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
        // 拦截所有
        filterMap.put("/*", "authc");


        shiroFilterFactoryBean.setLoginUrl("/tologin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }
```
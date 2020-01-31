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
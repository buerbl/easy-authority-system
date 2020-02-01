package com.example.demo;

import com.example.demo.entity.ShiroUser;
import com.example.demo.service.IShiroUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {

    @Autowired
    private IShiroUserService shiroUserService;
    @Test
    public void contextLoads() {

        ShiroUser user = shiroUserService.getUser("root", "123456");
        log.info("结果为:{}", user.toString());
    }

}

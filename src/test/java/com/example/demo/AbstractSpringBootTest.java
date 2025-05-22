package com.example.demo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p> 基础测试类 </p>
 * <p>Created on 2024/4/2.</p>
 *
 * @author kennylee
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@Transactional
@ActiveProfiles({"dev", "local", "test", "my"})
public abstract class AbstractSpringBootTest {
}
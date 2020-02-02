package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/2 21:04
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new GlobalCorsConfig());
        registration.addUrlPatterns("/*");
        registration.setName("GlobalCorsConfig");
        registration.setOrder(1);
        return registration;
    }

}




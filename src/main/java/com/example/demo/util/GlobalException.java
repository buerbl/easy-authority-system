package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/3 14:35
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {

    // 拦截器
    @ExceptionHandler({UnauthorizedException.class})
    public Result exception(UnauthorizedException e) {
        Result result = new Result();
        result.setCode(Code.NORIGHT.getCode());
        result.setData(Code.NORIGHT.getMsg());
        log.error("报错了", e);
        return result;
    }

    @ExceptionHandler({Exception.class})
    public Result exception(Exception e) {
        Result result = new Result();
        result.setCode(Code.ERROR.getCode());
        result.setData(e.getMessage());
        log.error("报错了", e);
        return result;
    }


}



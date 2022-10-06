package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    /**
     * 所有验证框架异常捕获处理
     * @return
     */
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public Object validationExceptionHandler(Exception exception) {
        BindingResult bindResult = null;
        if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        String msg;
        if (bindResult != null && bindResult.hasErrors()) {
            msg = bindResult.getAllErrors().get(0).getDefaultMessage();
            if (msg.contains("NumberFormatException")) {
                msg = "参数类型错误！";
            }
        }else {
            msg = "系统繁忙，请稍后重试...";
        }
        Result result = new Result();
        result.setCode(Code.ERROR.getCode());
        result.setData(msg);
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



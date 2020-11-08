package com.example.demo.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Code {
    SUCCESS(200, "成功"),
    USERNAMEERROR(501, "用户名错误"),
    PASSWORDERROR(502, "密码错误"),
    NORIGHT(503, "没有授权"),
    NOSIGNIN(504, "没有登录"),
    ERROR(500, "报错了");
    private int code;
    private String msg;


}

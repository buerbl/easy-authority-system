package com.example.demo.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author 布尔bl
 * @create 2020/4/27 15:39
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    RIGHT(1, "正常"),
    WRONG(0, "灰色"),
    ;
    private int code;
    private String msg;
}

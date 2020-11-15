package com.example.demo.enumUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @Author 布尔bl
 * @create 2020/11/15 15:02
 */
@Getter
@AllArgsConstructor
public enum SexEnum {
    MAN(1, "男"),
    WOMAN(2, "女");

    private Integer code;
    private String msg;

    public static  String getMsg(Integer code){
        for (SexEnum msg : SexEnum.values()){
            if (msg.code.equals(code)){
                return msg.msg;
            }
        }
        return null;
    }
}

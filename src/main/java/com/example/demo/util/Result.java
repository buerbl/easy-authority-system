package com.example.demo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/2 23:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private T data;
    private Integer code;

    public  Result getResult(T data, Integer code){
        return new Result(data, code);
    }

}




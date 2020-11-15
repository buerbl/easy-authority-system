package com.example.demo.util;

import java.util.Objects;

/**
 * @Author 布尔bl
 * @create 2020/11/15 15:38
 */
public class RootUtil {
  public static String judgeRoot(Integer id){
      if (Objects.equals(id, 1)){
          throw new RuntimeException("超级用户信息无法进行操作");
      }
      return null;
  }
}

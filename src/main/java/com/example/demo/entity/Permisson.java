package com.example.demo.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author 布尔bl
 * @create 2020/4/26 18:09
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Permisson {
    @NotNull
    private Integer id;
    private String typName ;
    private String typePath  ;
    private Integer typePid;
    private List<Permisson> children;
}

package com.example.demo.controller;

import com.example.demo.entity.Permisson;
import com.example.demo.entity.Role;
import com.example.demo.service.IPermissionService;
import com.example.demo.util.BaseResult;
import com.example.demo.util.Code;
import com.example.demo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 布尔bl
 * @create 2020/4/25 18:00
 */
@Slf4j
@RestController
public class PermissonController extends BaseResult {
    @Autowired
    private IPermissionService permissionService;

    @PostMapping("/getPermisson")
    public Result getPermisson(@RequestBody @Validated Permisson dto){
        log.info("dto为：{}", dto.toString());
        Role role = permissionService.getPermisson(dto);
        return getResult(role, Code.SUCCESS.getCode());
    }

    @PostMapping("/getTree")
    public Result getTree(){
        return getResult(permissionService.getTree(), Code.SUCCESS.getCode());
    }


}

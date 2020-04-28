package com.example.demo.controller;

import com.example.demo.dto.ShiroUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.IShiroUserService;
import com.example.demo.service.IUserService;
import com.example.demo.util.BaseResult;
import com.example.demo.util.Code;
import com.example.demo.util.Result;
import com.example.demo.vo.ShiroUserVo;
import com.example.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 布尔bl
 * @create 2020/4/25 18:00
 */
@Slf4j
@RestController
public class UserControllerController extends BaseResult {
    @Autowired
    private IUserService userService;

    @PostMapping("/getUserInfo")
    public Result getUserInfo(@RequestBody @Validated User dto){
        log.info("dto为：{}", dto.toString());
        User user = userService.getUserInfo(dto);
        return getResult(user, Code.SUCCESS.getCode());
    }

    @PostMapping("/getUserPage")
    public Result getUserPage(@RequestBody UserDto dto){
        log.info("dto为：{}", dto.toString());
        UserVo userVo = userService.getUserPage(dto);
        return getResult(userVo, Code.SUCCESS.getCode());
    }

    @PostMapping("/changeStatuFlag")
    public Result changeStatuFlag(@RequestBody UserDto dto){
        log.info("dto为：{}", dto.toString());
//        UserVo userVo = userService.getUserPage(dto);
//        return getResult(userVo, Code.SUCCESS.getCode());
        return getResult(userService.changeStatuFlag(dto), Code.SUCCESS.getCode());
//        return null;
    }
}

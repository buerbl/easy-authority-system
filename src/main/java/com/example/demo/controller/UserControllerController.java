package com.example.demo.controller;

import com.example.demo.dto.ChangeStatuFlagDTO;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.example.demo.util.BaseResult;
import com.example.demo.util.Code;
import com.example.demo.util.Result;
import com.example.demo.vo.UserRoleVO;
import com.example.demo.vo.UserRoleVoPage;
import com.example.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Result getUserInfo(@RequestBody @Validated User dto) {
        log.info("dto为：{}", dto.toString());
        User user = userService.getUserInfo(dto);
        return getResult(user, Code.SUCCESS.getCode());
    }

    @PostMapping("/getUserInfoById")
    public Result getUserInfoById(@RequestBody @Validated Integer id) {
        log.info("查询单个用户信息开始");
        log.info("id：{}", id);
        User user = userService.getUserInfoById(id);
        log.info("查询单个用户信息结束");
        return getResult(user, Code.SUCCESS.getCode());
    }

    @PostMapping("/getUserPage")
    public Result getUserPage(@RequestBody UserDto dto) {
        log.info("dto为：{}", dto.toString());
        UserVo userVo = userService.getUserPage(dto);
        return getResult(userVo, Code.SUCCESS.getCode());
    }

    @RequiresPermissions("user - del")
    @PostMapping("/delUser")
    public Result delUser(@RequestBody Integer dto) {
        log.info("delUser-删除用户开始");
        log.info("dto为：{}", dto);
        Integer result = userService.delUser(dto);
        log.info("delUser-删除用户结束");
        return getResult(result, Code.SUCCESS.getCode());
    }

    @RequiresPermissions("user - update")
    @PostMapping("/changeStatuFlag")
    public Result changeStatuFlag(@RequestBody ChangeStatuFlagDTO changeStatuFlagDTO) {
        log.info("changeStatuFlag-改变状态开始");
        log.info("dto为：{}", changeStatuFlagDTO.toString());
        Boolean data = userService.changeStatuFlag(changeStatuFlagDTO);
        log.info("changeStatuFlag-改变状态结束");
        return getResult(data, Code.SUCCESS.getCode());
    }

    @RequiresPermissions("user - update")
    @PostMapping("/updateUserById")
    public Result updateUserById(@RequestBody @Validated User user) {
        log.info("updateUserById-更新用户开始");
        log.info("user为：{}", user.toString());
        Integer data = userService.updateUserById(user);
        log.info("updateUserById-更新用户结束");
        return getResult(data, Code.SUCCESS.getCode());
    }

    @RequiresPermissions("user - add")
    @PostMapping("/addUser")
    public Result addUser(@RequestBody @Validated User user) {
        log.info("addUser-更新用户开始");
        log.info("user为：{}", user.toString());
        Integer data = userService.addUser(user);
        log.info("addUser-更新用户结束");
        return getResult(data, Code.SUCCESS.getCode());
    }

    @PostMapping("/getUserRolePage")
    public Result getUserRolePage(@RequestBody @Validated UserDto user) {
        log.info("getUserRolePage-查询用户角色开始");
        log.info("user为：{}", user.toString());
        UserRoleVoPage data = userService.getUserRolePage(user);
        log.info("getUserRolePage-查询用户角色结束");
        return getResult(data, Code.SUCCESS.getCode());
    }


    @PostMapping("/export")
    public Result export() throws Exception {
        log.info("export-导出用户开始");
        userService.export();
        log.info("export-导出用户结束");
        return getResult("ok", Code.SUCCESS.getCode());
    }
}

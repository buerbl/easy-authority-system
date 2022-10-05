package com.example.demo.service;

import com.example.demo.dto.ChangeStatuFlagDTO;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.vo.UserRoleVO;
import com.example.demo.vo.UserRoleVoPage;
import com.example.demo.vo.UserVo;

import java.util.List;

public interface IUserService {

    User getUserInfo(User user);

    User getUser(String name, String password);

    User getUserByName(String name);

    UserVo getUserPage(UserDto dto);

    Boolean changeStatuFlag(ChangeStatuFlagDTO changeStatuFlagDTO);

    /**
     * 通过主键id得到用户信息
     *
     * @param id
     * @return
     */
    User getUserInfoById(Integer id);

    /**
     * 删除用户
     *
     * @param dto
     * @return
     */
    Integer delUser(Integer dto);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    Integer updateUserById(User user);

    Integer addUser(User user);

    UserRoleVoPage getUserRolePage(UserDto user);

    void addIpp(String ip, String location);

    /**
     * 导出excel
     */
    void export() throws Exception;
}

package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Permisson;
import com.example.demo.entity.ShiroUser;
import com.example.demo.entity.User;
import com.example.demo.vo.UserRoleVO;
import com.example.demo.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/1 14:30
 */
public interface UserMapper {

    User getUserInfo(@Param("user") User user);

    User getUser(@Param("name") String name, @Param("password") String password);

    List<User> getUserPage(@Param("name") String name, @Param("start") Integer start, @Param("size") Integer size);

    Integer getTotal(@Param("name") String name, @Param("start") Integer start, @Param("size") Integer size);

    Boolean changeStatuFlag(@Param("flag") Integer flag, @Param("id") Integer id);

    User getUserByName(@Param("name") String name);

    User getUserInfoById(@Param("id") Integer id);

    Integer delUser(@Param("id") Integer id);

    Integer updateUserById(User user);

    Integer addUser(User user);

    List<UserRoleVO> getUserRolePage(@Param("name") String name, @Param("start") Integer start, @Param("size") Integer size);


    UserRoleVO getUserRolePageFor1();

    Integer getUserRoleTotal(@Param("name") String name, @Param("start") Integer start, @Param("size") Integer size);

    void addIp(@Param("ip") String ip, @Param("location") String location);
}




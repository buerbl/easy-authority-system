package com.example.demo.service;

import com.example.demo.dto.ChangeStatuFlagDTO;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.vo.UserVo;

public interface IUserService {
    User getUserInfo(User user);

    User getUser(String name, String password);

    User getUserByName(String name);

    UserVo getUserPage(UserDto dto);

    Boolean changeStatuFlag(ChangeStatuFlagDTO changeStatuFlagDTO);
}

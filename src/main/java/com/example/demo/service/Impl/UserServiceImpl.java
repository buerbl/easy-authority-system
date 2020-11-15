package com.example.demo.service.Impl;

import com.example.demo.dto.ChangeStatuFlagDTO;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.enumUtil.SexEnum;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import com.example.demo.util.RootUtil;
import com.example.demo.util.StatusEnum;
import com.example.demo.vo.UserRoleVO;
import com.example.demo.vo.UserRoleVoPage;
import com.example.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/1 14:34
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    public User getUserInfo(User user) {
        return userMapper.getUserInfo(user);
    }

    @Override
    public User getUser(String name, String password) {
        log.info("name为{}, password为{}", name, password);
        return userMapper.getUser(name, password);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public UserVo getUserPage(UserDto dto) {
        String name = Optional.ofNullable(dto.getUser()).map(User::getName).orElse(null);
        String adress = Optional.ofNullable(dto.getUser()).map(User::getAdress).orElse(null);
        Integer start = (dto.getPagenum() - 1) * dto.getSize();
        Integer size = dto.getSize();
        List<User> userPage = userMapper.getUserPage(name, start, size);
        User userROOT = userMapper.getUserInfoById(1);
        userPage.add(0, userROOT);
        userPage.forEach(user -> {
            if (user.getStatus() == StatusEnum.RIGHT.getCode()) {
                user.setStatuFlag(true);
            }
            if (user.getStatus() == StatusEnum.WRONG.getCode()) {
                user.setStatuFlag(false);
            }
            user.setSexStr(SexEnum.getMsg(user.getSex()));
        });
        Integer total = userMapper.getTotal(name, start, size);
        UserVo userVo = new UserVo();
        userVo.setUserList(userPage);
        userVo.setTotal(total);
        return userVo;

    }

    @Override
    public Boolean changeStatuFlag(ChangeStatuFlagDTO changeStatuFlagDTO) {
        Integer flag = changeStatuFlagDTO.getStatus();
        Integer id = changeStatuFlagDTO.getId();
        RootUtil.judgeRoot(id);
        return userMapper.changeStatuFlag(flag, id);
    }

    @Override
    public User getUserInfoById(Integer id) {
        RootUtil.judgeRoot(id);
        User user = userMapper.getUserInfoById(id);
        return user;
    }

    @Override
    public Integer delUser(Integer dto) {
        RootUtil.judgeRoot(dto);
        Integer result = userMapper.delUser(dto);
        if (Objects.equals(result, 0)) {
            throw new RuntimeException("删除用户失败");
        }
        return result;
    }

    @Override
    public Integer updateUserById(User user) {
        RootUtil.judgeRoot(user.getId());
        Integer date = userMapper.updateUserById(user);
        if (Objects.equals(date, 0)) {
            throw new RuntimeException("更新用户失败");
        }
        return date;
    }

    @Override
    public Integer addUser(User user) {
        Integer date = userMapper.addUser(user);
        if (Objects.equals(date, 0)) {
            throw new RuntimeException("新增用户失败");
        }
        return date;
    }

    @Override
    public UserRoleVoPage getUserRolePage(UserDto dto) {
        String name = Optional.ofNullable(dto.getUser()).map(User::getName).orElse(null);
        Integer start = (dto.getPagenum() - 1) * dto.getSize();
        Integer size = dto.getSize();
        List<UserRoleVO> userRolePage  = userMapper.getUserRolePage(name, start,size);
        Integer total = userMapper.getUserRoleTotal(name, start, size);
        UserRoleVoPage userRoleVoPage = new UserRoleVoPage();
        userRoleVoPage.setUserList(userRolePage);
        userRoleVoPage.setTotal(total);
        return userRoleVoPage;
    }

}




package com.example.demo.service;

import com.example.demo.entity.Permisson;
import com.example.demo.entity.Role;
import com.example.demo.vo.PermissionVO;
import com.example.demo.vo.UserVo;

import java.util.List;

public interface IPermissionService {
    Role getPermisson(Permisson permisson);
    List<Permisson> getTree();

    List<PermissionVO> getPermisson(String roleName);
}

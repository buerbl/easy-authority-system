package com.example.demo.service;

import com.example.demo.entity.Permisson;
import com.example.demo.entity.Role;
import com.example.demo.vo.PermissionVO;

import java.util.List;

public interface IPermissionService {
    /**
     *
     * @param permisson
     * @return
     */
    Role getPermisson(Permisson permisson);


    List<Permisson> getTree();

    /**
     * 得到树形结构
     * @param roleName
     * @return
     */
    List<PermissionVO> getPermisson(String roleName);

    /**
     * 得到原始的数据
     * @param roleName
     * @return
     */
    List<PermissionVO> getPermissionList(String roleName);
}

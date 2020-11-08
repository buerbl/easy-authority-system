package com.example.demo.mapper;

import com.example.demo.entity.Permisson;
import com.example.demo.entity.Role;
import com.example.demo.vo.PermissionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description:
 * @Author: boolean
 * @Date: 2020/2/1 14:30
 * com.example.demo.mapper.PermissionMappercom.example.demo.mapper.PermissionMapper#getTree()
 */
@Mapper
public interface PermissionMapper {
    Role getPermission(Permisson permission);

    List<Permisson> getTree(int pid);

    List<PermissionVO> getPermissionByroleName(String roleName);
}




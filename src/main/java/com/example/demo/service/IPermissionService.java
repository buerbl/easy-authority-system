package com.example.demo.service;

import com.example.demo.entity.Permisson;
import com.example.demo.entity.Role;

import java.util.List;

public interface IPermissionService {
    Role getPermisson(Permisson permisson);
    List<Permisson> getTree();
}

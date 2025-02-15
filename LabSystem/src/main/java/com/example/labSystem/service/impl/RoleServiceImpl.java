package com.example.labSystem.service.impl;

import com.example.labSystem.domain.Role;
import com.example.labSystem.mappers.RoleMapper;
import com.example.labSystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public Role queryUserRole(String account){
        return roleMapper.queryUserRole(account);
    }
}

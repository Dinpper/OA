package com.example.labSystem.service.impl;

import com.example.labSystem.mappers.PermissionsMapper;
import com.example.labSystem.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionsMapper permissionsMapper;

    public List<String> queryPermissionsByRole(Integer roleId){
        List<String> list = permissionsMapper.queryPermissionsByRole(roleId);
        return list;
    }
}

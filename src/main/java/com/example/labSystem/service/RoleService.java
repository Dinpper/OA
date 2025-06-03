package com.example.labSystem.service;

import com.example.labSystem.domain.Role;
import com.example.labSystem.dto.RoleDto;

import java.util.List;

public interface RoleService {
    Role queryUserRole(String account);

    List<RoleDto> queryRoleList();
}

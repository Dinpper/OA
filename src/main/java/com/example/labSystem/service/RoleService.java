package com.example.labSystem.service;

import com.example.labSystem.domain.Role;

public interface RoleService {
    Role queryUserRole(String account);
}

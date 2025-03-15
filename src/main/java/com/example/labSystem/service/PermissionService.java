package com.example.labSystem.service;

import java.util.List;

public interface PermissionService {
    List<String> queryPermissionsByRole(Integer roleId);
}

package com.example.labSystem.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionsMapper {
    List<String> queryPermissionsByRole(@Param("roleId") Integer roleId);
}

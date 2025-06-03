package com.example.labSystem.mappers;

import com.example.labSystem.domain.Role;
import com.example.labSystem.dto.RoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    Role queryUserRole(@Param("account") String account);

    List<RoleDto> queryRoleList();
}

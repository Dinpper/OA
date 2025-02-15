package com.example.labSystem.mappers;

import com.example.labSystem.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {
    Role queryUserRole(@Param("account") String account);
}

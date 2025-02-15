package com.example.labSystem.mappers;

import com.example.labSystem.domain.Menus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenusMapper {
    List<Menus> queryMenusByRole(@Param("roleId") Integer roleId);
}

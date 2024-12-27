package com.example.labSystem.mappers;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersMapper {
    Integer queryIsUserExist(@Param("account") String account);
}

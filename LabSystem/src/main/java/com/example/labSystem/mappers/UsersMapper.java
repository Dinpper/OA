package com.example.labSystem.mappers;


import com.example.labSystem.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersMapper {
    Integer queryIsUserExist(@Param("account") String account);

    UserDto queryUserMessage(@Param("account") String account);
}

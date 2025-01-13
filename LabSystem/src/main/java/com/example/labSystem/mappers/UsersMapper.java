package com.example.labSystem.mappers;


import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.GroupUserDto;
import com.example.labSystem.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersMapper {
    Integer queryIsUserExist(@Param("account") String account);

    UserDto queryUserMessage(@Param("account") String account);

    List<Map<String, Object>> queryGroupUserAll();
}

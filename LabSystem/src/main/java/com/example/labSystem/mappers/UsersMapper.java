package com.example.labSystem.mappers;


import com.example.labSystem.domain.Users;
import com.example.labSystem.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersMapper {
    Integer queryIsUserExist(@Param("account") String account);

    UserDto queryUserMessage(@Param("account") String account);

    List<Map<String, Object>> queryGroupUserAll();

    Integer queryCountByPage(CommonRequestQto qto);

    List<UserExcelDto> queryUserByPage(CommonRequestQto qto);

    Integer updateUser(UserDto users);

    Integer deleteUser(@Param("account") String account);
}

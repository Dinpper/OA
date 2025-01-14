package com.example.labSystem.service;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.GroupUserDto;
import com.example.labSystem.dto.UserByPageDto;
import com.example.labSystem.dto.UserDto;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface UserService {

    UserDto queryUserMessage(CommonRequestQto qto);

    List<GroupUserDto> queryGroupUserAll();

    UserByPageDto queryUserByPage(CommonRequestQto qto);

    void download(HttpServletResponse response, CommonRequestQto qto) throws Exception;

    void updateUser(UserDto qto);

    void deleteUser(String account) throws Exception;
}

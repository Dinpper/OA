package com.example.labSystem.service;

import com.example.labSystem.dto.*;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface UserService {

    UserDto queryUserMessage(CommonRequestQto qto);

    List<GroupUserDto> queryGroupUserAll();

    UserByPageDto queryUserByPage(PageRequestQto qto);

    void download(HttpServletResponse response, PageRequestQto qto) throws Exception;

    void updateUser(UserDto qto);

    void deleteUser(String account) throws Exception;
}

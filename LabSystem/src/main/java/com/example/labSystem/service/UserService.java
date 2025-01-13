package com.example.labSystem.service;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.GroupUserDto;
import com.example.labSystem.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto queryUserMessage(CommonRequestQto qto);

    List<GroupUserDto> queryGroupUserAll();
}

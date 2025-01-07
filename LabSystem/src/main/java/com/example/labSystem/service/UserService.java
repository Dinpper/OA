package com.example.labSystem.service;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.UserDto;

public interface UserService {

    UserDto queryUserMessage(CommonRequestQto qto);
}

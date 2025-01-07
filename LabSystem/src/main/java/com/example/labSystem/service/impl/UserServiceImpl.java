package com.example.labSystem.service.impl;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.UserDto;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;


    @Override
    public UserDto queryUserMessage(CommonRequestQto qto) {
        String account = qto.getAccount();
        UserDto dto = usersMapper.queryUserMessage(account);
        return dto;
    }
}

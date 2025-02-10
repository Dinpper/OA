package com.example.labSystem.service.impl;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.LoginDto;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public LoginDto loginIn(CommonRequestQto qto) {
        LoginDto dto = new LoginDto();
        String userName = usersMapper.queryUserNameByAccount(qto.getAccount());
        dto.setUsername(userName);
        return dto;
    }
}

package com.example.labSystem.service.impl;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.LoginDto;
import com.example.labSystem.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {



    @Override
    public LoginDto loginIn(CommonRequestQto qto) {
        LoginDto dto = new LoginDto();
        return dto;
    }
}

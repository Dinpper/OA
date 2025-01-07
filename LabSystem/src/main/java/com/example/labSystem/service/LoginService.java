package com.example.labSystem.service;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.LoginDto;

public interface LoginService {
    LoginDto loginIn(CommonRequestQto qto);
}

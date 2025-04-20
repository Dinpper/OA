package com.example.labSystem.service;

import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.LoginDto;
import com.example.labSystem.dto.PermissionsInfoDto;

import java.util.Map;

public interface LoginService {
    void register(LoginDto dto) throws Exception;

    LoginDto loginIn(CommonRequestQto qto);

    Boolean authenticate(String account, String password);

    PermissionsInfoDto info(String account);
}

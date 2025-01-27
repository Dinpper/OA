package com.example.labSystem.controller;


import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.*;
import com.example.labSystem.service.LoginService;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController extends BaseController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/loginIn")
    public void loginIn(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody CommonRequestQto qto) throws Exception {
        String account = qto.getAccount();
        log.info("User {} loginIn", account);
        if(StringUtils.isEmpty(account)){
            throw new BusinessException(399, "参数错误");
        }
        LoginDto result = loginService.loginIn(qto);
        log.info("User {} queryHasDraft, result = {}", account, result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }
}
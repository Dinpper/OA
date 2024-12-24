package com.example.labSystem.controller;


import com.example.labSystem.dto.LoginInDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController // 用于 RESTful 服务
@RequestMapping("/login") // 类级别的请求映射
@Slf4j
public class LoginController {

    @PostMapping("/loginIn") // POST 请求映射
    public String loginIn(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginInDto loginDto) {
        String strTrue = "你过关了";
        String strFalse = "该罚";

        if (Objects.equals(loginDto.getUsername(), "Dinpper") && Objects.equals(loginDto.getPassword(), "labsystem")) {
            return strTrue;
        } else {
            return strFalse;
        }
    }
}
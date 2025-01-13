package com.example.labSystem.controller;


import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.GroupUserDto;
import com.example.labSystem.dto.JsonResultDto;
import com.example.labSystem.dto.UserDto;
import com.example.labSystem.service.UserService;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户相关
 *
 * @author Dinpper
 * @since 2025-01-06 16:13:49
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @PostMapping("/queryUserMessage")
    public void queryUserMessage(HttpServletRequest request, HttpServletResponse response,
                        @RequestBody CommonRequestQto qto) throws Exception {
        String account = qto.getAccount();
        log.info("User {} queryUserMessage,query = {}", account, GsonUtil.ObjectToJson(qto));
        if(StringUtils.isEmpty(account)){
            throw new BusinessException(399, "参数错误");
        }
        UserDto result = userService.queryUserMessage(qto);
        log.info("User {} queryHasDraft, result = {}", account, result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @PostMapping("/queryGroupUserAll")
    public void queryGroupUserAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<GroupUserDto> result = userService.queryGroupUserAll();
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }
}

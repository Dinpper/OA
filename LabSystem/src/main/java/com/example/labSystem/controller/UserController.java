package com.example.labSystem.controller;


import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.*;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.UserService;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/queryUserByPage", method = RequestMethod.POST)
    public void queryUserByPage(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody PageRequestQto qto) throws Exception {
        log.info("queryUserByPage,query = {}", GsonUtil.ObjectToJson(qto));
        UserByPageDto result = userService.queryUserByPage(qto);
        log.info("queryUserByPage, result = {}", result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void download(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody PageRequestQto qto) throws Exception {
        log.info("download,query = {}", GsonUtil.ObjectToJson(qto));
        userService.download(response, qto);
        log.info("download, 导出成功");
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "导出成功"));
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public void updateUser(HttpServletRequest request, HttpServletResponse response,
                           @RequestBody UserDto qto) throws Exception {
        String operator = qto.getOperator();
        String account = qto.getAccount();
        log.info("operator {} updateUser,query = {}", operator, GsonUtil.ObjectToJson(qto));
        if(StringUtils.isEmpty(account) || StringUtils.isEmpty(operator)){
            throw new BusinessException(399, "参数错误");
        }
        userService.updateUser(qto);
        log.info("operator {} updateUser {} 成功", operator, account);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "修改成功"));
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public void deleteUser(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody CommonRequestQto qto) throws Exception {
        String operator = qto.getOperator();
        String account = qto.getAccount();
        log.info("operator {} deleteUser,query = {}", operator, GsonUtil.ObjectToJson(qto));
        if(StringUtils.isEmpty(account) || StringUtils.isEmpty(operator)){
            throw new BusinessException(399, "参数错误");
        }
        userService.deleteUser(account);
        log.info("operator {} deleteUser {} 成功", operator, account);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "删除成功"));
    }
}

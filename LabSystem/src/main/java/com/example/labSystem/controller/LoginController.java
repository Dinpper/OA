package com.example.labSystem.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.example.labSystem.common.BusinessException;
import com.example.labSystem.domain.Role;
import com.example.labSystem.dto.*;
import com.example.labSystem.mappers.RoleMapper;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.LoginService;
import com.example.labSystem.service.MenuService;
import com.example.labSystem.service.PermissionService;
import com.example.labSystem.service.RoleService;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController // 用于 RESTful 服务
@RequestMapping("/login") // 类级别的请求映射
@Slf4j
public class LoginController extends BaseController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMapper roleMapper;

    @PostMapping("/loginIn")
    public void loginIn(HttpServletRequest request, HttpServletResponse response,
                        @RequestBody CommonRequestQto qto) throws Exception {
        String account = qto.getAccount();
        log.info("User {} loginIn", account);
        if (StringUtils.isEmpty(account)) {
            throw new BusinessException(399, "参数错误");
        }

        // 调用登录服务进行身份验证
//        LoginDto result = loginService.loginIn(qto);
//        log.info("User {} login result = {}", account, result);

        // 登录成功，生成 token
//        StpUtil.login(account); // 使用账号生成 token

        Map<String, Object> result = new HashMap<>();
//        result.put("token", StpUtil.getTokenValue());
        result.put("userName", usersMapper.queryUserNameByAccount(qto.getAccount()));
        result.put("role", roleMapper.queryUserRole(qto.getAccount()).getRoleName());


        // 调用统一的返回方法
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response,
                      @RequestBody CommonRequestQto qto) throws Exception {
        // 1. 验证用户名和密码
        loginService.authenticate(qto.getAccount(), qto.getPassword());

        // 2. 生成 Sa-Token 登录认证
        StpUtil.login(qto.getAccount());

        // 3. 生成返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("token", StpUtil.getTokenValue());

        // 4. 返回 JSON 结果
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping("isLogin")
    public void isLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result;
        if (StpUtil.isLogin()) {
            result = "已登录";
        } else {
            result = "未登录";
        }
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping("info")
    public void info(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String account = StpUtil.getLoginIdAsString();
        PermissionsInfoDto result = loginService.info(account);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

}
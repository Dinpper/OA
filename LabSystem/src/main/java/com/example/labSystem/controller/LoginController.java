package com.example.labSystem.controller;


import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.*;
import com.example.labSystem.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        if (StringUtils.isEmpty(account)) {
            throw new BusinessException(399, "参数错误");
        }
        LoginDto result = loginService.loginIn(qto);
        log.info("User {} queryHasDraft, result = {}", account, result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

//    @PostMapping("/doLogin")
//    public String doLogin(HttpServletRequest request, HttpServletResponse response,
//                          @RequestBody CommonRequestQto qto) throws Exception {
//        String account = qto.getAccount();
//        log.info("User {} doLogin", account);
//        if (StringUtils.isEmpty(account)) {
//            throw new BusinessException(399, "参数错误");
//        }
//        if ("Dinpper".equals(qto.getAccount()) && "123456".equals(qto.getPassword())) {
//            StpUtil.login(qto.getAccount());
//            return "登录成功";
//        }
//        return "登录失败";
//    }

//    @RequestMapping(value = "/doLoginn", method = RequestMethod.POST)
//    public String doLoginn(HttpServletRequest request, HttpServletResponse response,
//                           @RequestBody CommonRequestQto qto) {
//        // 模拟从数据库中查询用户
//        if ("zhang".equals(qto.getUserName()) && "123456".equals(qto.getPassword())) {
//            // 登录成功，生成 Token
//            StpUtil.login(10001);  // 用户 ID 10001 登录
//            // 返回 Token 信息
//            String token = StpUtil.getTokenValue();
//            return "登录成功，Token 为: " + token;  // 返回 Token 给前端
//        }
//        return "登录失败，用户名或密码错误";
//    }
//    /**
//     * 登录
//     *
//     * @param loginDTO
//     * @return
//     */
//    @PostMapping("login")
//    public SaResult login(@RequestBody LoginDto loginDto) {
//        log.info("111111111111111111111111111111111");
//        // 第一步：比对前端提交的账号名称、密码
//        if("zhang".equals(loginDto.getUsername()) && "123456".equals(loginDto.getPassword())) {
//            // 第二步：根据账号id，进行登录
//            StpUtil.login(10001);
//            return SaResult.ok("登录成功");
//        }
//        return SaResult.error("登录失败");
//    }


//    @GetMapping("/islogin")
//    public String test() {
//        return "当前会话是否登录：" + StpUtil.isLogin();
//    }
}
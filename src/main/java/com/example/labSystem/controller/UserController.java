package com.example.labSystem.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.io.FileUtil;
import com.example.labSystem.common.BusinessException;
import com.example.labSystem.domain.Report;
import com.example.labSystem.dto.*;
import com.example.labSystem.mappers.ReportMapper;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.RoleService;
import com.example.labSystem.service.SparkManagerService;
import com.example.labSystem.service.UserService;
import com.example.labSystem.utils.GsonUtil;
import com.example.labSystem.utils.ImageCombinerUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SparkManagerService sparkManagerService;

    @Autowired
    private ImageCombinerUtil imageCombinerUtil;


    @PostMapping("/timeline")
    public void getTimeLine(@RequestBody CommonRequestQto qto,HttpServletResponse response) throws IOException {
        String account = qto.getAccount();
        String year = qto.getYear();
        log.info("User {} queryUserMessage,query = {}", account, GsonUtil.ObjectToJson(qto));
        if(StringUtils.isEmpty(account)){
            throw new BusinessException(399, "参数错误");
        }
        List<Report> reports = reportMapper.getReportsByAccountAndYear(account, year);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, reports));
    }

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
    @RequestMapping(value = "/updateUsers", method = RequestMethod.POST)
    public void updateUsers(HttpServletRequest request, HttpServletResponse response,
                            @RequestBody BatchUpdateUserDto batchDto) throws Exception {
        String operator = batchDto.getOperator();
        List<UserDto> users = batchDto.getUsers();
        log.info("operator {} 批量 updateUsers, size = {}", operator, users.size());

        if (StringUtils.isEmpty(operator) || users == null || users.isEmpty()) {
            throw new BusinessException(399, "参数错误");
        }

        userService.updateUsers(batchDto);
        log.info("operator {} 批量更新用户成功", operator);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "批量修改成功"));
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

    @RequestMapping(value = "/generatePersonalSummary", method = RequestMethod.POST)
    public void generatePersonalSummary(HttpServletRequest request, HttpServletResponse response,
                           @RequestBody CommonRequestQto qto) throws Exception {

        String result = sparkManagerService.generatePersonalSummary(qto.getAccount());
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/updateUserRole", method = RequestMethod.POST)
    public void updateUserRole(HttpServletRequest request, HttpServletResponse response,
                           @RequestBody UserDto qto) throws Exception {
        userService.updateUserRole(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "修改成功"));
    }

    @RequestMapping(value = "/queryRoleList", method = RequestMethod.POST)
    public void queryRoleList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<RoleDto> result = roleService.queryRoleList();
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }





    // 测试登录，浏览器访问： http://localhost:8880/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public String doLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8880/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }


    @PostMapping("/post")
    public ResponseEntity<byte[]> generatePost(@RequestBody PostElementsDto postElements) throws Exception {
        if (postElements == null){
            throw new BusinessException(502,"参数信息有误");
        }
        imageCombinerUtil.generatePost(postElements);
        String filePath="/home/portrait/post/post_"
                + DigestUtils.md5DigestAsHex(postElements.getAccount().getBytes()) + ".png";
        System.out.println("文件名："+filePath);
        File file = FileUtil.file(filePath);
        try {
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + filePath);
            }
            // 使用Hutool获取文件的MIME类型
            String mimeType = FileUtil.getMimeType(filePath);
            System.out.println("MIME类型 = " + mimeType);
            // 设置响应头，告诉浏览器这是一个下载的文件，并设置文件名
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + file.getName());
            headers.add("Content-Type", mimeType); // 可以根据图片类型设置
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");

            // 返回文件内容，ResponseEntity 发送字节流给前端
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(FileUtil.readBytes(file)); // 使用Hutool读取文件字节数组
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

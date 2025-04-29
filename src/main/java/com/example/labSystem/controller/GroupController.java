package com.example.labSystem.controller;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.*;
import com.example.labSystem.service.GroupService;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小组相关
 *
 * @author Dinpper
 * @since 2025-01-14 00:36:49
 */
@RestController
@RequestMapping("/group")
@Slf4j
public class GroupController extends BaseController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/queryGroupsList")
    public void queryGroupsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> result = groupService.queryGroupsList();
        log.info("queryGroupsList, result = {}", result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @PostMapping("/queryReportGroupsList")
    public void queryReportGroupsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<GroupUserDto> result = groupService.queryReportGroupsList();
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @PostMapping("/queryGroupsByPage")
    public void queryGroupsByPage(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody PageRequestQto qto) throws Exception {
        log.info("queryGroupsByPage,query = {}", GsonUtil.ObjectToJson(qto));
        GroupByPageDto result = groupService.queryGroupsByPage(qto);
        log.info("queryGroupsByPage, result = {}", result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void download(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody PageRequestQto qto) throws Exception {
        String operator = qto.getOperator() == null ? "没传操作人" : qto.getOperator();
        log.info("operator {} updateGroup", qto.getOperator());
        groupService.download(response, qto);
        log.info("operator {} updateGroup, 导出成功", qto.getOperator());
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "导出成功"));
    }

    @RequestMapping(value = "/updateGroup", method = RequestMethod.POST)
    public void updateGroup(HttpServletRequest request, HttpServletResponse response,
                           @RequestBody GroupDto qto) throws Exception {
        String operator = qto.getOperator();
        String account = qto.getAccount();
        log.info("operator {} updateGroup,query = {}", operator, GsonUtil.ObjectToJson(qto));
        if(StringUtils.isEmpty(account) || StringUtils.isEmpty(operator)){
            throw new BusinessException(399, "参数错误");
        }
        groupService.updateGroup(qto);
        log.info("operator {} updateGroup {} 成功", operator, account);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "修改成功"));
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
    public void deleteGroup(HttpServletRequest request, HttpServletResponse response,
                           @RequestBody CommonRequestQto qto) throws Exception {
        String operator = qto.getOperator();
        String groupName = qto.getGroupName();
        log.info("operator {} deleteGroup,query = {}", operator, GsonUtil.ObjectToJson(qto));
        if(StringUtils.isEmpty(groupName) || StringUtils.isEmpty(operator)){
            throw new BusinessException(399, "参数错误");
        }
        groupService.deleteGroup(groupName);
        log.info("operator {} deleteGroup {} 成功", operator, groupName);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "删除成功"));
    }

    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public void addGroup(HttpServletRequest request, HttpServletResponse response,
                            @RequestBody CommonRequestQto qto) throws Exception {
        String operator = qto.getOperator();
        String groupName = qto.getGroupName();
        log.info("operator {} addGroup,query = {}", operator, GsonUtil.ObjectToJson(qto));
        if(StringUtils.isEmpty(groupName) || StringUtils.isEmpty(operator)){
            throw new BusinessException(399, "参数错误");
        }
        groupService.addGroup(qto);
        log.info("operator {} addGroup {} 成功", operator, groupName);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "添加成功"));
    }
}

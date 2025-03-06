package com.example.labSystem.controller;

import com.example.labSystem.common.BusinessException;
import com.example.labSystem.dto.*;
import com.example.labSystem.service.LeaveService;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/leave")
@Slf4j
public class LeaveController extends BaseController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/addLeave")
    public void addLeave(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody LeaveDto qto) throws Exception {
        if (StringUtils.isEmpty(qto.getAccount())) {
            throw new BusinessException(399, "参数错误");
        }
        if (StringUtils.isEmpty(qto.getReason())) {
            throw new BusinessException(399, "参数错误");
        }
        if (qto.getStartDate() == null) {
            throw new BusinessException(399, "参数错误");
        }
        if (qto.getEndDate() == null) {
            throw new BusinessException(399, "参数错误");
        }
        leaveService.addLeave(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "发起成功"));
    }

    @PostMapping("/approveLeave")
    public void approveLeave(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody LeaveDto qto) throws Exception {
        leaveService.approveLeave(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "发起成功"));
    }

    @PostMapping("/notApprovedLeave")
    public void notApprovedLeave(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody LeaveDto qto) throws Exception {
        leaveService.notApprovedLeave(qto);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, "发起成功"));
    }

    @RequestMapping(value = "/queryPendingLeaveByPage", method = RequestMethod.POST)
    public void queryPendingLeaveByPage(HttpServletRequest request, HttpServletResponse response,
                                        @RequestBody PageRequestQto qto) throws Exception {
        log.info("queryPendingLeaveByPage,query = {}", GsonUtil.ObjectToJson(qto));
        LeaveByPageDto result = leaveService.queryPendingLeaveByPage(qto);
        log.info("queryPendingLeaveByPage, result = {}", result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

    @RequestMapping(value = "/queryLeaveByPage", method = RequestMethod.POST)
    public void queryLeaveByPage(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody PageRequestQto qto) throws Exception {
        log.info("queryLeaveByPage,query = {}", GsonUtil.ObjectToJson(qto));
        LeaveByPageDto result = leaveService.queryLeaveByPage(qto);
        log.info("queryLeaveByPage, result = {}", result);
        BackJsonResult(response, new JsonResultDto(JsonResultDto.CODE_OK, result));
    }

}

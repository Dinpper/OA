package com.example.labSystem.controller;

import com.example.labSystem.dto.JsonResultDto;
import com.example.labSystem.dto.LeaveDto;
import com.example.labSystem.service.LeaveService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/leave")
@Slf4j
public class LeaveController extends BaseController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/addLeave")
    public void addLeave(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody LeaveDto qto) throws Exception {
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

}
